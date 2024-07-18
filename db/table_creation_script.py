import mysql.connector
from mysql.connector import Error

try:
    # Connect to MySQL
    conn = mysql.connector.connect(
        host='localhost',
        database='FilmDB',
        user='root',
        password='2003'
    )
    
    if conn.is_connected():
        cursor = conn.cursor()
        
        # Drop tables if they exist to start fresh (optional)
        tables = ['PAYMENT', 'RENTAL', 'STAFF', 'CUSTOMER', 'INVENTORY', 'FILM_CATEGORY', 'FILM_ACTOR', 'ACTOR', 'FILM', 'CATEGORY', 'LANGUAGE', 'STORE', 'ADDRESS', 'CITY', 'COUNTRY']
        for table in tables:
            cursor.execute(f"DROP TABLE IF EXISTS {table}")

        # Create COUNTRY table
        cursor.execute("""
        CREATE TABLE COUNTRY (
            COUNTRY_ID INT AUTO_INCREMENT PRIMARY KEY,
            COUNTRY NVARCHAR(50),
            LAST_UPDATE DATETIME
        )
        """)

        # Create CITY table
        cursor.execute("""
        CREATE TABLE CITY (
            CITY_ID INT AUTO_INCREMENT PRIMARY KEY,
            CITY NVARCHAR(50),
            COUNTRY_ID INT,
            LAST_UPDATE DATETIME,
            FOREIGN KEY (COUNTRY_ID) REFERENCES COUNTRY(COUNTRY_ID)
        )
        """)

        # Create ADDRESS table
        cursor.execute("""
        CREATE TABLE ADDRESS (
            ADDRESS_ID INT AUTO_INCREMENT PRIMARY KEY,
            ADDRESS NVARCHAR(100),
            ADDRESS2 NVARCHAR(100),
            DISTRICT NVARCHAR(50),
            CITY_ID INT,
            POSTAL_CODE NVARCHAR(20),
            PHONE NVARCHAR(20),
            LAST_UPDATE DATETIME,
            FOREIGN KEY (CITY_ID) REFERENCES CITY(CITY_ID)
        )
        """)

        # Create STORE table
        cursor.execute("""
        CREATE TABLE STORE (
            STORE_ID INT AUTO_INCREMENT PRIMARY KEY,
            ADDRESS_ID INT,
            LAST_UPDATE DATETIME,
            FOREIGN KEY (ADDRESS_ID) REFERENCES ADDRESS(ADDRESS_ID)
        )
        """)

        # Create LANGUAGE table
        cursor.execute("""
        CREATE TABLE LANGUAGE (
            LANGUAGE_ID INT AUTO_INCREMENT PRIMARY KEY,
            NAME NVARCHAR(50),
            LAST_UPDATE DATETIME
        )
        """)

        # Create CATEGORY table
        cursor.execute("""
        CREATE TABLE CATEGORY (
            CATEGORY_ID INT AUTO_INCREMENT PRIMARY KEY,
            NAME NVARCHAR(50),
            LAST_UPDATE DATETIME
        )
        """)

        # Create FILM table
        cursor.execute("""
        CREATE TABLE FILM (
            FILM_ID INT AUTO_INCREMENT PRIMARY KEY,
            TITLE NVARCHAR(255),
            DESCRIPTION TEXT,
            RELEASE_YEAR INT,
            LANGUAGE_ID INT,
            ORIGINAL_LANGUAGE_ID INT,
            RENTAL_DURATION INT,
            RENTAL_RATE DECIMAL(4,2),
            LENGTH INT,
            REPLACEMENT_COST DECIMAL(5,2),
            RATING NVARCHAR(10),
            SPECIAL_FEATURES NVARCHAR(100),
            LAST_UPDATE DATETIME,
            FOREIGN KEY (LANGUAGE_ID) REFERENCES LANGUAGE(LANGUAGE_ID),
            FOREIGN KEY (ORIGINAL_LANGUAGE_ID) REFERENCES LANGUAGE(LANGUAGE_ID)
        )
        """)

        # Create ACTOR table
        cursor.execute("""
        CREATE TABLE ACTOR (
            ACTOR_ID INT AUTO_INCREMENT PRIMARY KEY,
            FIRST_NAME NVARCHAR(50),
            LAST_NAME NVARCHAR(50),
            LAST_UPDATE DATETIME
        )
        """)

        # Create FILM_ACTOR table
        cursor.execute("""
        CREATE TABLE FILM_ACTOR (
            FILM_ID INT,
            ACTOR_ID INT,
            LAST_UPDATE DATETIME,
            PRIMARY KEY (FILM_ID, ACTOR_ID),
            FOREIGN KEY (FILM_ID) REFERENCES FILM(FILM_ID),
            FOREIGN KEY (ACTOR_ID) REFERENCES ACTOR(ACTOR_ID)
        )
        """)

        # Create FILM_CATEGORY table
        cursor.execute("""
        CREATE TABLE FILM_CATEGORY (
            FILM_ID INT,
            CATEGORY_ID INT,
            LAST_UPDATE DATETIME,
            PRIMARY KEY (FILM_ID, CATEGORY_ID),
            FOREIGN KEY (FILM_ID) REFERENCES FILM(FILM_ID),
            FOREIGN KEY (CATEGORY_ID) REFERENCES CATEGORY(CATEGORY_ID)
        )
        """)

        # Create INVENTORY table
        cursor.execute("""
        CREATE TABLE INVENTORY (
            INVENTORY_ID INT AUTO_INCREMENT PRIMARY KEY,
            FILM_ID INT,
            STORE_ID INT,
            LAST_UPDATE DATETIME,
            FOREIGN KEY (FILM_ID) REFERENCES FILM(FILM_ID),
            FOREIGN KEY (STORE_ID) REFERENCES STORE(STORE_ID)
        )
        """)

        # Create CUSTOMER table
        cursor.execute("""
        CREATE TABLE CUSTOMER (
            CUSTOMER_ID INT AUTO_INCREMENT PRIMARY KEY,
            STORE_ID INT,
            FIRST_NAME NVARCHAR(50),
            LAST_NAME NVARCHAR(50),
            EMAIL NVARCHAR(50),
            ADDRESS_ID INT,
            ACTIVE BIT,
            CREATE_DATE DATETIME,
            LAST_UPDATE DATETIME,
            FOREIGN KEY (STORE_ID) REFERENCES STORE(STORE_ID),
            FOREIGN KEY (ADDRESS_ID) REFERENCES ADDRESS(ADDRESS_ID)
        )
        """)

        # Create STAFF table
        cursor.execute("""
        CREATE TABLE STAFF (
            STAFF_ID INT AUTO_INCREMENT PRIMARY KEY,
            FIRST_NAME NVARCHAR(50),
            LAST_NAME NVARCHAR(50),
            ADDRESS_ID INT,
            EMAIL NVARCHAR(50),
            STORE_ID INT,
            ACTIVE BIT,
            USERNAME NVARCHAR(50),
            PASSWORD NVARCHAR(50),
            LAST_UPDATE DATETIME,
            FOREIGN KEY (ADDRESS_ID) REFERENCES ADDRESS(ADDRESS_ID),
            FOREIGN KEY (STORE_ID) REFERENCES STORE(STORE_ID)
        )
        """)

        # Create RENTAL table
        cursor.execute("""
        CREATE TABLE RENTAL (
            RENTAL_ID INT AUTO_INCREMENT PRIMARY KEY,
            RENTAL_DATE DATETIME,
            INVENTORY_ID INT,
            CUSTOMER_ID INT,
            RETURN_DATE DATETIME,
            STAFF_ID INT,
            LAST_UPDATE DATETIME,
            FOREIGN KEY (INVENTORY_ID) REFERENCES INVENTORY(INVENTORY_ID),
            FOREIGN KEY (CUSTOMER_ID) REFERENCES CUSTOMER(CUSTOMER_ID),
            FOREIGN KEY (STAFF_ID) REFERENCES STAFF(STAFF_ID)
        )
        """)

        # Create PAYMENT table
        cursor.execute("""
        CREATE TABLE PAYMENT (
            PAYMENT_ID INT AUTO_INCREMENT PRIMARY KEY,
            CUSTOMER_ID INT,
            STAFF_ID INT,
            RENTAL_ID INT,
            AMOUNT DECIMAL(5,2),
            PAYMENT_DATE DATETIME,
            LAST_UPDATE DATETIME,
            FOREIGN KEY (CUSTOMER_ID) REFERENCES CUSTOMER(CUSTOMER_ID),
            FOREIGN KEY (STAFF_ID) REFERENCES STAFF(STAFF_ID),
            FOREIGN KEY (RENTAL_ID) REFERENCES RENTAL(RENTAL_ID)
        )
        """)

        # Commit the transaction
        conn.commit()
        print("Tables created successfully")

except Error as e:
    print(f"Error: '{e}'")

finally:
    # Close the database connection
    if conn.is_connected():
        cursor.close()
        conn.close()
        print("MySQL connection is closed")
