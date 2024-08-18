import json
import os
import shutil

from utils.get_mappings import one_to_many, many_to_one
from utils.conversions import camel_case, pascal_case, determine_primary_keys, map_sql_type_to_java

# Stores many to one relationships
many = dict()
# Stores one to many relationships
one = dict()

with open('db_structure.json', 'r') as file:
    db_structure = json.load(file)

# Function to generate ID class for Entity
def generate_id_class(db_structure, output_dir, table_index, primary_keys, package_name, many, one):
    table_name = list(db_structure.keys())[table_index]
    details = db_structure[table_name]
    class_name = pascal_case(table_name)

    entity_dir = os.path.join(output_dir, 'entity')
    os.makedirs(entity_dir, exist_ok=True)

    # Generate class
    entity_path = os.path.join(entity_dir, f"{class_name}Id.java")
    
    with open(entity_path, 'w') as f:
        f.write(f"package {package_name}.entity;\n\n")
        
        f.write("import java.io.Serializable;\n")
        f.write(f"import java.util.Objects;\n\n")

        f.write(f"public class {class_name}Id implements Serializable {{\n")  
        for pk in primary_keys:
            id_of_pk = "Long"
            for col in details["columns"]:
                if col["name"] == pk:
                    id_of_pk = col["type"]
                    id_of_pk = map_sql_type_to_java(id_of_pk)
                    break

            f.write(f"private {id_of_pk} {camel_case(pk)};\n")

        # Constructors
        ## No Args Constructor
        f.write(f"public {class_name}Id (){{}}\n\n")

        ## Args Constructor
        parameters = ""
        set_pks = ""
        for pk in primary_keys:
            id_of_pk = "Long"
            for col in details["columns"]:
                if col["name"] == pk:
                    id_of_pk = col["type"]
                    id_of_pk = map_sql_type_to_java(id_of_pk)
                    break
            if parameters != "":
                parameters += ", "
            parameters += f"{id_of_pk} {camel_case(pk)}"
            set_pks += f"this.{camel_case(pk)} = {camel_case(pk)};\n"
        f.write(f"public {class_name}Id ({parameters}) {{\n")
        f.write(f"{set_pks}")
        f.write("}\n\n")


        # Getters
        for pk in primary_keys:
            id_of_pk = "Long"
            for col in details["columns"]:
                if col["name"] == pk:
                    id_of_pk = col["type"]
                    id_of_pk = map_sql_type_to_java(id_of_pk)
                    break
            f.write(f"public {id_of_pk} get{pascal_case(pk)}() {{\n")
            f.write(f"return this.{camel_case(pk)};\n")
            f.write("}\n\n")
        # Setters
        for pk in primary_keys:
            id_of_pk = "Long"
            for col in details["columns"]:
                if col["name"] == pk:
                    id_of_pk = col["type"]
                    id_of_pk = map_sql_type_to_java(id_of_pk)
                    break
            f.write(f"public void set{pascal_case(pk)} ({id_of_pk} {camel_case(pk)}) {{\n")
            f.write(f"this.{camel_case(pk)}={camel_case(pk)};\n")
            f.write("}\n\n")

        # equals method
        f.write("@Override\n")
        f.write("public boolean equals(Object o) {\n")
        f.write("   if (this == o) return true;\n")
        f.write("   if (o == null || getClass() != o.getClass()) return false;\n")
        f.write(f"  {class_name}Id that = ({class_name}Id) o;\n")
        
        obj_equals = ""
        for pk in primary_keys:
            if obj_equals != "":
                obj_equals += " && "
            obj_equals += f"Objects.equals({camel_case(pk)}, that.{camel_case(pk)})"
        
        f.write(f"  return {obj_equals};\n")
        f.write("}\n\n")

        # hashCode method
        f.write("@Override\n")
        f.write("public int hashCode() {\n")
        pk_ids = ""
        for i in range(len(primary_keys)-1):
            if pk_ids != "":
                pk_ids += ", "
            pk_ids += camel_case(primary_keys[i])
        pk_ids += ", " + camel_case(primary_keys[-1])
            
        f.write(f"  return Objects.hash({pk_ids});\n")
        f.write("}\n\n")

        f.write("}")


# Function to generate the DTO class
def generate_dto_class(db_structure, output_dir, table_index, package_name, many, one):
    table_name = list(db_structure.keys())[table_index]
    details = db_structure[table_name]
    class_name = pascal_case(table_name)
    object_name = camel_case(table_name)

    dto_dir = os.path.join(output_dir, 'dto')
    os.makedirs(dto_dir, exist_ok=True)

    # DTO class
    dto_path = os.path.join(dto_dir, f"{class_name}DTO.java")
    with open(dto_path, 'w') as f:
        f.write(f"package {package_name}.dto;\n\n")

        f.write(f"import {package_name}.entity.{class_name};\n")
        f.write("import java.util.List;\n")
        f.write("import java.util.stream.Collectors;\n\n")

        f.write(f"public class {class_name}DTO {{\n")
        for column in details["columns"]:
            column_name = column['name']
            column_type = column['type']
            java_type = map_sql_type_to_java(column_type)
            if column_name not in many:
                f.write(f"      private {java_type} {camel_case(column_name)};\n")

        for column in one.keys():
            f.write(f"      private List<{pascal_case(column)}DTO> {camel_case(column)};\n")

        f.write("\n") # Adds Empty line

        # No Args Constructor
        f.write(f"      public {class_name}DTO() {{")
        f.write("       }\n\n")       

        # convertToDTO() function
        f.write(f"      public static {class_name}DTO convertToDTO({class_name} {object_name}) {{\n")
        f.write(f"          {class_name}DTO {object_name}DTO = new {class_name}DTO();\n")
        for column in details['columns']:
            column_name = column['name']
            if column_name in many: continue
            f.write(f"          {object_name}DTO.set{pascal_case(column_name)}({object_name}.get{pascal_case(column_name)}());\n")
        f.write("\n")
        for column in one.keys():
            f.write(f"          List<{pascal_case(column)}DTO> {camel_case(column)}DTO = {object_name}.get{pascal_case(column)}().stream().map({pascal_case(column)}DTO::convertToDTO).collect(Collectors.toList());\n")
            f.write(f"          {object_name}DTO.set{pascal_case(column)}({camel_case(column)}DTO);\n")

        f.write(f"          return {object_name}DTO;\n")
        f.write("       }\n\n")

        # convertToEntity() function
        f.write(f"      public {class_name} convertToEntity() {{\n")
        f.write(f"          {class_name} {object_name} = new {class_name}();\n")
        for column in details['columns']:
            column_name = column['name']
            if column_name in many: continue
            f.write(f"          {object_name}.set{pascal_case(column_name)}(this.{camel_case(column_name)});\n")
        for column in one.keys():
            f.write(f"          {object_name}.set{pascal_case(column)}(this.{camel_case(column)}.stream().map({pascal_case(column)}DTO::convertToEntity).collect(Collectors.toList()));\n")
        f.write(f"          return {object_name};\n")
        f.write("       }\n\n")


        # Getters and Setters
        for column in details['columns']:
            column_name = column['name']
            column_type = column['type']

            java_type = map_sql_type_to_java(column_type)
            camel_case_name = camel_case(column_name)

            # Getter
            if column_name in many:
                continue
            else: 
                f.write(f"public {java_type} get{pascal_case(column_name)}() {{\n")
            f.write(f"      return this.{camel_case_name};\n")
            f.write("}\n\n")

            # Setter
            if column_name in many:
                continue
            else: 
                f.write(f"public void set{pascal_case(column_name)}({java_type} {camel_case_name}) {{\n")
            f.write(f"      this.{camel_case_name} = {camel_case_name};\n")
            f.write("}\n\n")

        
        # Getter setter for one_to_many
        for column in one.keys():
            # Getter
            f.write(f"public List<{pascal_case(column)}DTO> get{pascal_case(column)}() {{\n")
            f.write(f"      return this.{camel_case(column)};\n")
            f.write("}\n\n")
            # Setter
            f.write(f"public void set{pascal_case(column)}(List<{pascal_case(column)}DTO> {camel_case(column)}) {{\n")
            f.write(f"      this.{camel_case(column)} = {camel_case(column)};\n")
            f.write("}\n\n")

        f.write("}\n")
        print(f"Generate DTO Class for {class_name}DTO")


# Function to generate the entity class
def generate_entity_class(db_structure, output_dir, package_name, schema, many, one, table_name, details, primary_keys):
    # table_name = list(db_structure.keys())[table_index]
    # details = db_structure[table_name]
    class_name = pascal_case(table_name)
    object_name = camel_case(table_name)
    id_type = "Integer"
    num_of_primary_keys = len(primary_keys)

    # Determine primary keys
    # primary_keys = determine_primary_keys(details)
    # print(f"Primary keys for {table_name}: {primary_keys}")

    # Create entity directory
    entity_dir = os.path.join(output_dir, 'entity')
    os.makedirs(entity_dir, exist_ok=True)

    # Generate Entity Class
    entity_path = os.path.join(entity_dir, f"{class_name}.java")
    
    with open(entity_path, 'w') as f:
        f.write(f"package {package_name}.entity;\n\n")

        f.write("import jakarta.persistence.*;\n")
        f.write(f"import java.util.*;\n\n")

        for i in one.keys():
            f.write(f"import {package_name}.entity.{pascal_case(i)};\n")

        f.write(f"@Entity\n@Table(name = \"{table_name}\", schema = \"{schema}\")\n")

        # Generate ID Class if composite primary key
        if num_of_primary_keys>1:
            generate_id_class(db_structure, output_dir,  table_index, primary_keys, package_name, many, one)
            
        
        f.write(f"public class {class_name} {{\n")
        if num_of_primary_keys>1:
            f.write("   @EmbeddedId\n")
            id_type = f"{class_name}Id"
            f.write(f"  private {id_type} {object_name}Id;\n")
        for column in details['columns']:
            column_name = column['name']
            column_type = column['type']
            java_type = map_sql_type_to_java(column_type)
            if column_name in primary_keys and len(primary_keys) == 1:
                f.write("@Id\n")
                if column_name in many:
                    id_type = pascal_case(many[column_name]['referred_table'])
                else: id_type = java_type
            if column_name in many:
                if (len(primary_keys)>1 and column_name not in primary_keys) or len(primary_keys)==1:
                    f.write("@ManyToOne\n")
                    f.write(f"@JoinColumn(name=\"{many[column_name]['constrained_columns'][0]}\", nullable = true)\n")
                    f.write(f"private {pascal_case(many[column_name]['referred_table'])} {camel_case(column_name)};\n\n")
                if len(primary_keys)>1 and column_name in primary_keys:
                    f.write(f"@ManyToOne\n")
                    f.write(f'@MapsId("{camel_case(column_name)}")\n')
                    f.write(f'@JoinColumn(name = "{many[column_name]["constrained_columns"][0]}", nullable = true)\n')
                    f.write(f"private {pascal_case(many[column_name]['referred_table'])} {camel_case(many[column_name]['referred_table'])}Id;\n\n")
            else: 
                f.write(f"@Column(name=\"{column_name}\")\n")
                f.write(f"private {java_type} {camel_case(column_name)};\n\n")

        # One to Many Mapping
        for i in one.keys():    
            f.write(f"@OneToMany(mappedBy = \"{camel_case(one[i]['constrained_columns'][0])}\", cascade = CascadeType.ALL, fetch = FetchType.LAZY)\n")
            f.write(f"private List<{pascal_case(i)}> {camel_case(i)};\n\n")


        get_id_done = False
        set_id_done = False
        # Generate getters and setters
        for column in details['columns']:
            column_name = column['name']
            column_type = column['type']

            java_type = map_sql_type_to_java(column_type)
            camel_case_name = camel_case(column_name)

            # Getter
            if column_name in primary_keys:
                if len(primary_keys)>1 and get_id_done == False:
                    f.write(f"public {id_type} get{class_name}Id() {{\n")
                    f.write(f"return this.{object_name}Id;\n")
                    f.write("}\n\n")
                    get_id_done = True
                elif len(primary_keys)==1:
                    f.write(f"public {id_type} get{pascal_case(column_name)}() {{\n")
                    f.write(f"return this.{camel_case(column_name)};\n")
                    f.write("}\n\n")
            else:        
                if column_name in many:
                    f.write(f"public {pascal_case(many[column_name]['referred_table'])} get{pascal_case(column_name)}() {{\n")
                else: f.write(f"public {java_type} get{pascal_case(column_name)}() {{\n")
                f.write(f"      return this.{camel_case_name};\n")
                f.write("}\n\n")

            # Setter
            if column_name in primary_keys:
                if len(primary_keys)>1 and set_id_done == False:
                    f.write(f"public void set{class_name}Id ({id_type} {object_name}Id) {{\n")
                    f.write(f"this.{object_name}Id = {object_name}Id;\n")
                    f.write("}\n\n")
                    set_id_done = True
                elif len(primary_keys)==1:
                    f.write(f"public void set{pascal_case(column_name)} ({id_type} {camel_case(column_name)}) {{\n")
                    f.write(f"this.{camel_case(column_name)} = {camel_case(column_name)};\n")
                    f.write("}\n\n")
            else:
                if column_name in many:
                    f.write(f"public void set{pascal_case(column_name)}({pascal_case(many[column_name]['referred_table'])} {camel_case(column_name)}) {{\n")
                else: 
                    f.write(f"public void set{pascal_case(column_name)}({java_type} {camel_case_name}) {{\n")
                f.write(f"      this.{camel_case_name} = {camel_case_name};\n")
                f.write("}\n\n")


        # Getter setter for one_to_many
        for column in one.keys():
            # Getter
            f.write(f"public List<{pascal_case(column)}> get{pascal_case(column)}() {{\n")
            f.write(f"      return this.{camel_case(column)};\n")
            f.write("}\n\n")
            # Setter
            f.write(f"public void set{pascal_case(column)}(List<{pascal_case(column)}> {camel_case(column)}) {{\n")
            f.write(f"      this.{camel_case(column)} = {camel_case(column)};\n")
            f.write("}\n\n")

        f.write("}\n")

        print(f"Entity class {class_name}.java generated successfully.")
        return id_type
        

# Function to generate the repository interface for the first table in the schema
def generate_repository_class(db_structure, output_dir, table_index, package_name, id_type):
    try:
        table_name = list(db_structure.keys())[table_index]
        class_name = pascal_case(table_name)
        object_name = camel_case(table_name)

        # Create repository directory
        repository_dir = os.path.join(output_dir, 'repository')
        os.makedirs(repository_dir, exist_ok=True)
        print(f"Repository directory created: {repository_dir}")

        # Generate Repository Interface
        repository_path = os.path.join(repository_dir, f"{class_name}Repository.java")
        with open(repository_path, 'w') as f:
            f.write(f"package {package_name}.repository;\n\n")
            f.write(f"import org.springframework.data.jpa.repository.JpaRepository;\n")
            f.write(f"import {package_name}.entity.{class_name};\n\n")
            if (id_type.startswith(class_name)):
                f.write(f"import {package_name}.entity.{class_name}Id;\n\n")
            f.write(f"public interface {class_name}Repository extends JpaRepository<{class_name}, {id_type}> {{\n")
            f.write("}\n")

        print(f"Repository interface {class_name}Repository.java created successfully.")

    except Exception as e:
        print(f"Error generating repository interface: {e}")

# Function to generate the service interface and implementation for the first table in the schema
def generate_service_classes(db_structure, output_dir, table_index, package_name, id_type, many, one):
    try:
        table_name = list(db_structure.keys())[table_index]
        details = db_structure[table_name]
        primary_keys = determine_primary_keys(details)
        class_name = pascal_case(table_name)
        object_name = camel_case(table_name)

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
            f.write(f"import {package_name}.dto.{class_name}DTO; \n\n")
            f.write(f"import java.util.List;\n\n")
            if (id_type.startswith(class_name)):
                f.write(f"import {package_name}.entity.{class_name}Id;\n\n")
            f.write(f"public interface {class_name}Service {{\n")
            f.write(f"List<{class_name}DTO> findAll();\n")
            f.write(f"List<{class_name}DTO> findById({id_type} id);\n")
            f.write(f"{class_name}DTO updateById({id_type} id, {class_name}DTO {object_name}DTO);\n")
            f.write(f"void deleteById({id_type} id);\n")
            f.write(f"{class_name}DTO insert{class_name}({class_name}DTO {object_name}DTO);\n")
            f.write("}\n")

        print(f"Service interface {class_name}Service.java generated successfully.")

        # Generate Service Implementation
        service_impl_path = os.path.join(service_impl_dir, f"{class_name}ServiceImpl.java")

        with open(service_impl_path, 'w') as f:
            f.write(f"package {package_name}.service.impl;\n\n")

            f.write(f"import {package_name}.dto.{class_name}DTO;\n")
            f.write(f"import {package_name}.entity.{class_name};\n")
            f.write(f"import {package_name}.repository.{class_name}Repository;\n")
            f.write(f"import {package_name}.service.{class_name}Service;\n\n")

            f.write("import org.springframework.beans.factory.annotation.Autowired;\n")
            f.write("import org.springframework.stereotype.Service;\n")
            f.write("import org.springframework.transaction.annotation.Transactional;\n\n")

            f.write("import java.util.List;\n")
            f.write("import java.util.stream.Collectors;\n\n")
            f.write("import java.util.Collections;\n")
            f.write("import java.util.Optional;\n")
            if (id_type.startswith(class_name)):
                f.write(f"import {package_name}.entity.{class_name}Id;\n\n")

            f.write(f"@Service\n")
            f.write(f"public class {class_name}ServiceImpl implements {class_name}Service {{\n\n")
            f.write(f"      @Autowired\n")
            f.write(f"      private {class_name}Repository {object_name}Repository;\n\n")

            f.write(f"      @Override\n")
            f.write(f"      public List<{class_name}DTO> findAll() {{\n")
            f.write(f"          List<{class_name}> {object_name} = {object_name}Repository.findAll();\n")
            f.write(f"          return {object_name}.stream().map({class_name}DTO::convertToDTO).collect(Collectors.toList());\n")
            f.write("      }\n\n")

            f.write(f"      @Override\n")
            f.write(f"      public List<{class_name}DTO> findById({id_type} id){{\n")
            f.write(f"          Optional<{class_name}> {object_name} = {object_name}Repository.findById(id);\n")
            f.write(f"          return {object_name}.map(f -> Collections.singletonList({class_name}DTO.convertToDTO(f))).orElse(Collections.emptyList());\n")
            f.write("      }\n\n")

            
            f.write(f"      @Override\n")
            f.write(f"      public {class_name}DTO updateById({id_type} id, {class_name}DTO {object_name}DTO) {{\n")
            f.write(f"          Optional<{class_name}> optional{class_name} = {object_name}Repository.findById(id);\n")
            f.write(f"          if (optional{class_name}.isPresent()) {{\n")
            f.write(f"              {class_name} {object_name} = optional{class_name}.get();\n")
            f.write(f"              {object_name} = {object_name}DTO.convertToEntity();\n")
            f.write(f"              {object_name}.set{class_name}Id(id);\n")
            f.write(f"              {object_name}Repository.save({object_name});\n")
            f.write(f"              return {class_name}DTO.convertToDTO({object_name});\n")
            f.write("          } else {\n")
            f.write(f"              return null;\n")
            f.write("          }\n")
            f.write("      }\n\n")

            f.write(f"      @Transactional\n")
            f.write(f"      @Override\n")
            f.write(f"      public void deleteById({id_type} id) {{\n")
            f.write(f"          Optional<{class_name}> optional{class_name} = {object_name}Repository.findById(id);\n")
            f.write(f"          if (optional{class_name}.isPresent()){{\n")
            f.write(f"              {object_name}Repository.deleteById(id);\n")
            f.write("           } else { \n")
            f.write('               throw new RuntimeException("Film not found");\n')
            f.write('           }\n')
            f.write('       }\n\n')
                
            f.write(f"      @Override\n")
            f.write(f"      public {class_name}DTO insert{class_name} ({class_name}DTO {object_name}DTO) {{\n")
            f.write(f"          {class_name} {object_name} = {object_name}DTO.convertToEntity();\n")
            f.write(f"          {class_name} saved{class_name} = {object_name}Repository.save({object_name});\n")
            f.write(f"          return {class_name}DTO.convertToDTO(saved{class_name});\n")
            f.write("      }\n\n")

            f.write("}\n")

        print(f"Service implementation {class_name}ServiceImpl.java generated successfully.")

    except Exception as e:
        print(f"Error generating service classes: {e}")

# Function to generate the controller class for the first table in the schema
def generate_controller_class(db_structure, output_dir, table_index, package_name, id_type, api_logs, primary_keys):
    try:
        table_name = list(db_structure.keys())[table_index]
        class_name = pascal_case(table_name)
        object_name = camel_case(table_name)
        num_of_primary_keys = len(primary_keys)

        # Create controller directory
        controller_dir = os.path.join(output_dir, 'controller')
        os.makedirs(controller_dir, exist_ok=True)
        print(f"Controller directory created: {controller_dir}")

        # Generate Controller Class
        controller_path = os.path.join(controller_dir, f"{class_name}Controller.java")
        with open(controller_path, 'w') as f:
            f.write(f"package {package_name}.controller;\n\n")
            f.write(f"import {package_name}.dto.{class_name}DTO;\n")
            f.write(f"import {package_name}.service.{class_name}Service;\n")
            f.write(f"import org.springframework.beans.factory.annotation.Autowired;\n")
            f.write(f"import org.springframework.web.bind.annotation.*;\n")
            f.write(f"import java.util.List;\n\n")
            if (id_type.startswith(class_name)):
                f.write(f"import {package_name}.entity.{class_name}Id;\n\n")
            f.write(f"@RestController\n")
            f.write(f"@RequestMapping(\"{table_name}\")\n")
            f.write(f"public class {class_name}Controller {{\n\n")
            
            f.write(f"      @Autowired\n")
            f.write(f"      private {class_name}Service {object_name}Service;\n\n")
            

            ### FindAll
            f.write(r"      @GetMapping")
            f.write("\n")
            f.write(f"      public List<{class_name}DTO> findAll() {{\n")
            f.write(f"          return {object_name}Service.findAll();\n")
            f.write("      }\n")

            mappingStr = ''
            argsStr = ''
            list_of_primary_keys = ""
            if num_of_primary_keys>1:
                
                for pk in primary_keys:
                    mappingStr += '/{' + f'{camel_case(pk)}' + '}'
                    list_of_primary_keys += camel_case(pk) + ", "
                    id_of_pk = "Long"
                    for col in details["columns"]:
                        if col["name"] == pk:
                            id_of_pk = col["type"]
                            id_of_pk = map_sql_type_to_java(id_of_pk)
                            break
                    argsStr += f'@PathVariable {id_of_pk} {camel_case(pk)}, '
                

            ### FindById
            if num_of_primary_keys > 1:
                f.write(f'      @GetMapping("{mappingStr}")\n')
                f.write(f"      public List<{class_name}DTO> findById({argsStr[:-2]}){{\n")
                f.write(f"          {id_type} id = new {id_type}({list_of_primary_keys[:-2]});\n")
            if num_of_primary_keys == 1:
                f.write(r'      @GetMapping("/{id}")')
                f.write("\n")
                f.write(f"      public List<{class_name}DTO> findById(@PathVariable {id_type} id){{\n")

            f.write(f"          List<{class_name}DTO> {object_name} = {object_name}Service.findById(id);\n")
            f.write(f"          return {object_name}.isEmpty() ? null : {object_name};\n")
            f.write("      }\n\n")


            # UpdateById
            if num_of_primary_keys > 1:
                f.write(f'      @PutMapping("{mappingStr}")\n')
                f.write(f"      public {class_name}DTO updateById({argsStr} @RequestBody {class_name}DTO {object_name}DTO) {{\n")
                f.write(f"          {id_type} id = new {id_type}({list_of_primary_keys[:-2]});\n")
            if num_of_primary_keys == 1:
                f.write(r'      @PutMapping("/{id}")')
                f.write("\n")
                f.write(f"      public {class_name}DTO updateById(@PathVariable {id_type} id, @RequestBody {class_name}DTO {object_name}DTO) {{\n")
            f.write(f"          return {object_name}Service.updateById(id, {object_name}DTO);\n")
            f.write("      }\n\n")


            ### DeleteById
            if num_of_primary_keys > 1:
                f.write(f'      @DeleteMapping("{mappingStr}")\n')
                f.write(f"      public void deleteById({argsStr[:-2]}) {{\n")
                f.write(f"          {id_type} id = new {id_type}({list_of_primary_keys[:-2]});\n")
            if num_of_primary_keys == 1:
                f.write(r'      @DeleteMapping("/{id}")')
                f.write("\n")
                f.write(f"      public void deleteById(@PathVariable {id_type} id) {{\n")
            f.write(f"          {object_name}Service.deleteById(id);\n")
            f.write("      }\n\n")

            ### Insert
            f.write(f'      @PostMapping\n')
            f.write(f'      public {class_name}DTO insert{class_name} (@RequestBody {class_name}DTO {object_name}DTO) {{\n')
            f.write(f'          return {object_name}Service.insert{class_name}({object_name}DTO);\n')
            f.write('       }\n')

            f.write("}\n")
        api_logs[table_name] = [f"http://localhost:8080/{table_name}", f"http://localhost:8080/{table_name}/id"]
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


####################################################################### Define output directory #######################################################################

current_dir = os.getcwd()
# output_dir_in = input("Enter output directory: ")
# output_dir = os.path.join(current_dir, output_dir_in)
# package_name_in = input("Enter package name: ")
# package_name = package_name_in
output_dir = os.path.join(current_dir, "src/main/java/com/wellsfargo/demo")
package_name = "com.example.demo"

api_logs = dict()

for table_index in range(len(db_structure)):

    table_name = list(db_structure.keys())[table_index]
    many = many_to_one(table_name, db_structure)
    one = one_to_many(table_name, db_structure)
    details = db_structure[table_name]
    primary_keys = determine_primary_keys(details)

    print(table_name, primary_keys)

    # Entity
    id_type = generate_entity_class(db_structure, output_dir, package_name, "test_dbo.dbo", many, one,table_name, details, primary_keys)
    print("Entity class generated successfully.")

    # DTO
    generate_dto_class(db_structure, output_dir, table_index, package_name, many, one)
    print("DTO class generated successfully.")

    # Repository
    generate_repository_class(db_structure, output_dir, table_index, package_name, id_type)
    print("Repository class generated successfully.")

    # Service
    generate_service_classes(db_structure, output_dir, table_index, package_name, id_type, many, one)
    print("Service class generated successfully.")

    # Controller
    generate_controller_class(db_structure, output_dir, table_index, package_name, id_type, api_logs, primary_keys)
    print("Controller class generated successfully.")

#Saving API logs
with open('apilogs.json', 'w') as file:
    json.dump(api_logs, file, indent=4, default=str)

# Define source and destination libs
src_dir = os.path.join(current_dir, output_dir)
dest_dir = r"C:\Users\ranaDherya\Desktop\demo\src\main\java\com\example\demo"

# Call the function to copy the files
copy_files_to_destination(src_dir, dest_dir)