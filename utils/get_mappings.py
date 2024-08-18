import json 

# Finds one to many relationships for input table
def one_to_many(table_name, db_structure):

    mapping = dict()
    for tbl in db_structure.keys():
        if table_name == tbl: continue
        for fk in db_structure[tbl]["foreign_keys"]:
            if fk["referred_table"] == table_name:
                mapping[tbl] = fk
                break

    return mapping

# Finds many to one relationships for input table
def many_to_one(table_name, db_structure):
    
    mapping = dict()
    foreign_keys = db_structure[table_name]["foreign_keys"]
    for fk in foreign_keys:
        mapping[fk["constrained_columns"][0]] = fk

    return mapping

# print(one_to_many("FILM"))
# print(many_to_one("FILM_ACTOR"))