# camelCase
def camel_case(snake_str): 
    components = snake_str.split("_")
    return components[0].lower() + ''.join(x.title() for x in components[1:])

# PascalCase
def pascal_case(snake_str): 
    return ''.join(word.title() for word in snake_str.split("_"))

# Sql to Java type mapping
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