from sqlalchemy import create_engine, MetaData, inspect
from sqlalchemy.exc import SQLAlchemyError
from sqlalchemy.engine.reflection import Inspector
import json
import os
import shutil

from mappings import one_to_many, many_to_one

# DATABASE_URL = ''


# def connect_to_database():
#     try:
#         engine = create_engine(DATABASE_URL.format(password="0908"))
#         metadata = MetaData()
#         metadata.reflect(bind=engine)

#         print("Database connection successfull")

#         return engine
#     except Exception as e:
#         print(e)
#         return None


# engine = connect_to_database()
# inspector = inspect(engine)

# if engine is None or inspector is None:
#     print("Connection to database failed")
#     exit()


# def camel_case(snake_str):
#     components = snake_str.split('_')
#     return components[8] + ''.join(x.titlle() for x in components[1:])


# def pascal_case(snake_str):
#     return ''.join(x.title() for x in snake_str.split('_'))


# def get_table_details(table_name):
#     columns = inspector.get_columns(table_name)
#     primary_keys = inspector.get_pk_constraint(table_name)
#     foreign_keys = inspector.get_foreign_keys(table_name)
#     indexes = inspector.get_indexes(table_name)

#     table_info = {
#         "columns": columns,
#         "primary_keys": primary_keys,
#         "foreign_keys": foreign_keys,
#         "indexes": indexes
#     }
#     return table_info


# def get_database_structure():
#     tables = inspector.get_table_names()
#     db_structure = {}
#     for table in tables:
#         table_details = get_table_details(table)
#         if (table_details['columns']):
#             db_structure[table] = table_details
#     return db_structure


# db_structure = get_database_structure()
# with open('db_structure.json', 'w') as file:
#     json.dump(db_structure, file, indent=4, default=str)

# print("Database schema saved")


with open('db_structure.json', 'r') as file:
    db_structure = json.load(file)

# Utility functions

def camel_case(snake_str):
    components = snake_str.split("_")
    return components[0] + ''.join(x.title() for x in components[1:])

def pascal_case(snake_str):
    return ''.join(word.title() for word in snake_str.split("_"))

# Java type mapping
def map_sql_type_to_java(sql_type):
    type_mapping = {
        "INTEGER": "Integer",
        "SMALLINT": "Short",
        "BIGINT": "Long",
        "REAL": "Float",
        "FLOAT": "Double",
        "NUMERIC": "java.math.BigDecimal",
        "DECIMAL": "java.math.BigDecimal",
        "CHAR": "String",
        "VARCHAR": "String",
        "DATE": "java.sql.Date",
        "TIME": "java.sql.Time",
        "TIMESTAMP": "java.sql.Timestamp",
        "BOOLEAN": "Boolean",
        "BINARY": "byte[]",
        "VARBINARY": "byte[]",
        "LONGVARBINARY": "byte[]",
        "CLOB": "java.sql.Clob",
        "BLOB": "java.sql.Blob"
    }

    return type_mapping.get(sql_type.upper(), "String")

# Function to determine primary keys from columns and indexes
def determine_primary_keys(details):
    primary_keys = details.get('primary_keys', {}).get('constrained_columns', [])
    if not primary_keys:
        primary_keys = []
        for index in details.get('indexes', []):
            if index.get('unique', False):
                primary_keys.extend(index.get('column_names', []))

    return primary_keys

# Function to generate the entity class
def generate_entity_class(db_structure, output_dir, table_index, package_name, schema):
    table_name = list(db_structure.keys())[table_index]
    details = db_structure[table_name]
    class_name = pascal_case(table_name)
    # Determine primary keys
    primary_keys = determine_primary_keys(details)
    print(f"Primary keys for {table_name}: {primary_keys}")

    # Create entity directory
    entity_dir = os.path.join(output_dir, 'entity')
    os.makedirs(entity_dir, exist_ok=True)

    # Generate Entity Class
    entity_path = os.path.join(entity_dir, f"{class_name}.java")
    
    with open(entity_path, 'w') as f:
        f.write(f"package {package_name}.entity;\n\n")
        f.write("import javax.persistence.*;\n")
        f.write(f"import java.util.*;\n")
        f.write(f"import java.math.BigDecimal;\n")
        f.write(f"import java.time.*;\n\n")

        f.write(f"@Entity\n@Table(name = \"{table_name}\", schema = \"{schema}\")\n")
        f.write(f"public class {class_name} {{\n")
        for column in details['columns']:
            column_name = column['name']
            column_type = column['type']
            java_type = map_sql_type_to_java(column_type)
            if column_name in primary_keys:
                f.write("@Id")
            f.write(f"@Column(name=\"{column_name}\")")
            f.write(f"private {java_type} {camel_case(column_name)};\n\n")

        # Generate getters and setters
        for column in details['columns']:
            column_name = column['name']
            column_type = column['type']

            java_type = map_sql_type_to_java(column_type)
            camel_case_name = camel_case(column_name)

            # Getter
            f.write(f"public {java_type} get{pascal_case(column_name)}() {{\n")
            f.write(f"      return this.{camel_case_name};\n")
            f.write("}\n\n")

            # Setter
            f.write(f"public void set{pascal_case(column_name)}({java_type} {camel_case_name}) {{\n")
            f.write(f"      this.{camel_case_name} = {camel_case_name};\n")
            f.write("}\n\n")

        f.write("}\n")

        print(f"Entity class {class_name}.java generated successfully.")

# Function to generate the repository interface for the first table in the schema
def generate_repository_class(db_structure, output_dir, table_index, package_name):
    try:
        table_name = list(db_structure.keys())[table_index]
        class_name = pascal_case(table_name)
        # Create repository directory
        repository_dir = os.path.join(output_dir, 'repository')
        os.makedirs(repository_dir, exist_ok=True)
        print(f"Repository directory created: {repository_dir}")

        # Generate Repository Interface
        repository_path = os.path.join(repository_dir, f"{class_name}Repository.java")
        with open(repository_path, 'w') as f:
            f.write(f"package {package_name}.repository;\n\n")
            f.write(f"import org.springframework.data.jpa.repository.JpaRepository;\n")
            f.write(f"import {package_name}.entity.{class_name};]\n\n")
            f.write(f"public interface {class_name}Repository extends JpaRepository<{class_name}, Long> {{\n")
            f.write("}\n")

        print(f"Repository interface {class_name}Repository.java created successfully.")

    except Exception as e:
        print(f"Error generating repository interface: {e}")

# Function to generate the service interface and implementation for the first table in the schema
def generate_service_classes(db_structure, output_dir, table_index, package_name):
    try:
        table_name = list(db_structure.keys())[table_index]
        class_name = pascal_case(table_name)

        # Create service directories
        service_dir = os.path.join(output_dir, 'service')
        service_impl_dir = os.path.join(service_dir, 'impl')
        os.makedirs(service_dir, exist_ok=True)
        os.makedirs(service_impl_dir, exist_ok=True)
        print(f"Service directories created: {service_dir}, {service_impl_dir}")

        # Generate Service Interface
        service_interface_path = os.path.join(service_dir, f"{class_name}Service.java")
        with open(service_interface_path, 'w') as f:
            f.write(f"package {package_name}.service;\n\n")
            f.write(f"import {package_name}.entity.{class_name}; \n\n")
            f.write(f"import java.util.List;\n\n")
            f.write(f"public interface {class_name}Service {{\n")
            f.write(f"List<{class_name}>findAll();\n")
            f.write("}\n")

        print(f"Service interface {class_name}Service.java generated successfully.")

        # Generate Service Implementation
        service_impl_path = os.path.join(service_impl_dir, f"{class_name}ServiceImpl.java")
        with open(service_impl_path, 'w') as f:
            f.write(f"package {package_name}.service.impl;\n\n")
            f.write(f"import {package_name}.entity.{class_name};\n")
            f.write(f"import {package_name}.repository.{class_name}Repository;\n")
            f.write(f"import {package_name}.service.{class_name}Service;\n")
            f.write(f"import org.springframework.beans.factory.annotation.Autowired;\n")
            f.write(f"import org.springframework.stereotype.Service;\n\n")
            f.write(f"import java.util.List;\n\n")
            f.write(f"@Service\n")
            f.write(f"public class {class_name}ServiceImpl implements {class_name}Service {{\n\n")
            f.write(f"      @Autowired\n")
            f.write(f"      private {class_name}Repository {table_name}Repository;\n\n")
            f.write(f"      @Override\n")
            f.write(f"      public List<{class_name}> findAll() {{\n")
            f.write(f"          return {table_name}Repository.findAll();\n")
            f.write(f"      }}\n")
            f.write("}\n")

        print(f"Service implementation {class_name}ServiceImpl.java generated successfully.")

    except Exception as e:
        print(f"Error generating service classes: {e}")

# Function to generate the controller class for the first table in the schema
def generate_controller_class(db_structure, output_dir, table_index, package_name):
    try:
        table_name = list(db_structure.keys())[table_index]
        class_name = pascal_case(table_name)

        # Create controller directory
        controller_dir = os.path.join(output_dir, 'controller')
        os.makedirs(controller_dir, exist_ok=True)
        print(f"Controller directory created: {controller_dir}")

        # Generate Controller Class
        controller_path = os.path.join(controller_dir, f"{class_name}Controller.java")
        with open(controller_path, 'w') as f:
            f.write(f"package {package_name}.controller;\n\n")
            f.write(f"import {package_name}.entity.{class_name};\n")
            f.write(f"import {package_name}.service.{class_name}Service;\n")
            f.write(f"import org.springframework.beans.factory.annotation.Autowired;\n")
            f.write(f"import org.springframework.web.bind.annotation.GetMapping;\n")
            f.write(f"import org.springframework.web.bind.annotation.RequestMapping;\n")
            f.write(f"import org.springframework.web.bind.annotation.RestController;\n\n")
            f.write(f"import java.util.List;\n\n")
            f.write(f"@RestController\n")
            f.write(f"@RequestMapping(\"{table_name}\")\n")
            f.write(f"public class {class_name}Controller {{\n\n")
            f.write(f"      @Autowired\n")
            f.write(f"      private {class_name}Service {table_name}Service;\n\n")
            f.write(f"      @GetMapping\n")
            f.write(f"      public List<{class_name}> findAll() {{\n")
            f.write(f"          return {table_name}Service.findAll();\n")
            f.write(f"      }}\n")
            f.write("}\n")

        print(f"Controller class {class_name}Controller.java generated successfully.")
    
    except Exception as e:
        print(f"Error generating controller class: {e}")

def copy_files_to_destination(src_dir, dest_dir):
    # Check if source directory exists
    if not os.path.exists(src_dir):
        print(f"Source directory {src_dir} does not exist.")
        return 
    
    # Create destination directory if it doesn't exist
    os.makedirs(dest_dir, exist_ok=True)

    # Copy each file or directory in the source directory to the destination directory
    for filename in os.listdir(src_dir):
        src_path = os.path.join(src_dir, filename)
        dest_path = os.path.join(dest_dir, filename)
        try:
            if os.path.isfile(src_path):
                shutil.copy(src_path, dest_path)
            elif os.path.isdir(src_path):
                shutil.copytree(src_path, dest_path)
        except Exception as e:
            print(f"Error copying {filename}: {e}")


######################## Define output directory #############################

current_dir = os.getcwd()
output_dir = os.path.join(current_dir, 'src/main/java/com')
package_name = "com.wellsfargo.lpo"

for table_index in range(len(db_structure)):

    # Entity
    generate_entity_class(db_structure, output_dir, table_index, package_name, "exception.dbo")
    print("Entity class generated successfully.")

    # Repository
    generate_repository_class(db_structure, output_dir, table_index, package_name)
    print("Repository class generated successfully.")

    # Service
    generate_service_classes(db_structure, output_dir, table_index, package_name)
    print("Service class generated successfully.")

    # Controller
    generate_controller_class(db_structure, output_dir, table_index, package_name)
    print("Controller class generated successfully.")

# Define source and destination libs
src_dir = os.path.join(current_dir, 'src/,ain/java/com/')
dest_dir = r""

# Call the function to copy the files
copy_files_to_destination(src_dir, dest_dir)