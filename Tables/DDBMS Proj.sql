/*Oracle DDL statements for restaurant database*/

/*reference data*/
CREATE TABLE menu_item
  ( 
     menuID VARCHAR(255) NOT NULL PRIMARY KEY, 
     price  VARCHAR(255) 
  ); 
  
CREATE TABLE food
 ( 
     foodID VARCHAR(255) NOT NULL PRIMARY KEY, 
     name   VARCHAR(255),
	 category VARCHAR(255),
	 menuID VARCHAR(255)
  );  
  
 CREATE TABLE drink
  ( 
     drinkID VARCHAR(255) NOT NULL PRIMARY KEY, 
     name   VARCHAR(255),
	 category VARCHAR(255),
	 menuID VARCHAR(255)
  );  
  
CREATE TABLE ingredient
  ( 
     ingredientID VARCHAR(255) NOT NULL PRIMARY KEY, 
	 amount VARCHAR(255), 
	 inventoryID VARCHAR(255),
	 menuID VARCHAR(255)
  );
  
 CREATE TABLE employee
  ( 
     employeeID VARCHAR(255) NOT NULL PRIMARY KEY, 
     name   VARCHAR(255), 
     positionID  VARCHAR(255)
  );
  
 CREATE TABLE position
  ( 
     positionID VARCHAR(255) NOT NULL PRIMARY KEY, 
	 payRate VARCHAR(255)
  );
  CREATE TABLE cocktailRecipe
  ( 
     drinkID VARCHAR(255) NOT NULL PRIMARY KEY, 
     name   VARCHAR(255), 
     ingredient  VARCHAR(255),
	 amount VARCHAR(255),
	 menuID VARCHAR(255)
  ); 
  
  /*transactional data*/
    CREATE TABLE sale
  ( 
     saleNumber INT NOT NULL PRIMARY KEY, 
     total  VARCHAR(255) 
  ); 

  CREATE TABLE customer
  ( 
     customerID INT NOT NULL PRIMARY KEY, 
     name   VARCHAR(255), 
     creditCardNo  VARCHAR(255),
	 saleNumber INT
  ); 
  
   CREATE TABLE inventory
  ( 
     inventoryID VARCHAR(255) NOT NULL PRIMARY KEY, 
     name   VARCHAR(255), 
     stockQuantity  VARCHAR(255),
	 vendor VARCHAR(255)
  );
  
   CREATE TABLE seating
  ( 
     tableID VARCHAR(255) NOT NULL PRIMARY KEY, 
     employeeID  VARCHAR(255), 
     visitDate VARCHAR (255),
	 saleNumber INT
  );
  CREATE TABLE customerOrder
  ( 
     saleNumber INT NOT NULL PRIMARY KEY, 
     menuID VARCHAR(255),
	 inventoryID VARCHAR(255)
  );
  
ALTER TABLE employee ADD CONSTRAINT fk_employee
FOREIGN KEY (positionID) REFERENCES position (positionID);

ALTER TABLE customer ADD CONSTRAINT fk_customer
FOREIGN KEY (saleNumber) REFERENCES sale (saleNumber);

ALTER TABLE customerOrder ADD CONSTRAINT fk_customerOrder1
FOREIGN KEY (saleNumber) REFERENCES sale (saleNumber);

ALTER TABLE customerOrder ADD CONSTRAINT fk_customerOrder2
FOREIGN KEY (menuID) REFERENCES menu_item (menuID);

ALTER TABLE food ADD CONSTRAINT fk_food
FOREIGN KEY (menuID) REFERENCES menu_item (menuID);

ALTER TABLE drink ADD CONSTRAINT fk_drink
FOREIGN KEY (menuID) REFERENCES menu_item (menuID);

ALTER TABLE cocktailRecipe ADD CONSTRAINT fk_cocktailRecipe
FOREIGN KEY (menuID) REFERENCES menu_item (menuID);

	 /*-- sample data*/
	INSERT INTO menu_item ( menuID ,price )
	VALUES ( '1a' ,
			 '10.25' );
	INSERT INTO menu_item ( menuID ,price )
	VALUES ( '1b' ,
			 '5.00' );	
	INSERT INTO menu_item ( menuID ,price )
	VALUES ( '10a' ,
			 '6.00' );
	INSERT INTO menu_item ( menuID ,price )
	VALUES ( '11a' ,
			 '4.00' );
			 
	INSERT INTO food ( foodID ,name, category, menuID )
	VALUES ( '10' ,
			 'cheeseburger', 'burgers', '1b');
	INSERT INTO food ( foodID ,name, category, menuID )
	VALUES ( '11' ,
			 'steak', 'entrees', '1a');
	INSERT INTO drink ( drinkID ,name, category, menuID )
	VALUES ( '100' ,
			 'martini', 'cocktails', '10a');
	INSERT INTO drink ( drinkID ,name, category, menuID )
	VALUES ( '101' ,
			 'margarita', 'cocktails', '11a');
	INSERT INTO inventory ( inventoryID ,name, stockQuantity, vendor )
	VALUES ( '1001a' ,
			 'salt', '20 lb', 'Morton');
	INSERT INTO ingredient ( ingredientID , amount, inventoryID, menuID )
	VALUES ( '1001a' ,
			 '1 oz', '1001a', '11a');		
	INSERT INTO position	( positionID , payRate )
	VALUES ( 'server' ,
			 3.00);		 
	INSERT INTO employee ( employeeID , name, positionID )
	VALUES ( '909561' ,
			 'Bret Micheals', 'server');	
	INSERT INTO cocktailRecipe ( drinkID , name, ingredient, amount, menuID )
	VALUES ( '100' ,
			 'martini', 'vermouth', '2 oz', '10a');			 
	INSERT INTO sale (saleNumber, total) 
	VALUES (1, '55.60');
	INSERT INTO customer ( customerID , name, creditCardNo, saleNumber )
	VALUES ( 10101521 ,
			 'Jon Snow', 1234567897654321, 1);	
	INSERT INTO seating ( tableID , employeeID, visitDate, saleNumber )
	VALUES ( 'S5-A' ,
			 '909561', '13-NOV-14', 1 );
	INSERT INTO customerOrder ( saleNumber , menuID, inventoryID )
	VALUES ( 1 ,
			 '1a', '1001a');

/*SQL Server DDL statements for restaurant database*/

/*reference data*/
CREATE TABLE menu_item
  ( 
     menuID VARCHAR(255) NOT NULL PRIMARY KEY, 
     price  VARCHAR(255) 
  ); 
  
CREATE TABLE food
 ( 
     foodID VARCHAR(255) NOT NULL PRIMARY KEY, 
     name   VARCHAR(255),
	 category VARCHAR(255),
	 menuID VARCHAR(255)
  );  
  
 CREATE TABLE drink
  ( 
     drinkID VARCHAR(255) NOT NULL PRIMARY KEY, 
     name   VARCHAR(255),
	 category VARCHAR(255),
	 menuID VARCHAR(255)
  );  
  
CREATE TABLE ingredient
  ( 
     ingredientID VARCHAR(255) NOT NULL PRIMARY KEY, 
	 amount VARCHAR(255), 
	 inventoryID VARCHAR(255),
	 menuID VARCHAR(255)
  );
  
 CREATE TABLE employee
  ( 
     employeeID VARCHAR(255) NOT NULL PRIMARY KEY, 
     name   VARCHAR(255), 
     positionID  VARCHAR(255)
  );
  
 CREATE TABLE position
  ( 
     positionID VARCHAR(255) NOT NULL PRIMARY KEY, 
	 payRate VARCHAR(255)
  );
  CREATE TABLE cocktailRecipe
  ( 
     drinkID VARCHAR(255) NOT NULL PRIMARY KEY, 
     name   VARCHAR(255), 
     ingredient  VARCHAR(255),
	 amount VARCHAR(255),
	 menuID VARCHAR(255)
  ); 
  
  /*transactional data*/
    CREATE TABLE sale
  ( 
     saleNumber INT NOT NULL PRIMARY KEY, 
     total  VARCHAR(255)
  ); 

  CREATE TABLE customer
  ( 
     customerID INT NOT NULL PRIMARY KEY, 
     name   VARCHAR(255), 
     creditCardNo  VARCHAR(255),
	 saleNumber INT
  ); 
  
   CREATE TABLE inventory
  ( 
     inventoryID VARCHAR(255) NOT NULL PRIMARY KEY, 
     name   VARCHAR(255), 
     stockQuantity  VARCHAR(255),
	 vendor VARCHAR(255)
  );
  
   CREATE TABLE seating
  ( 
	 tableID VARCHAR(255) NOT NULL PRIMARY KEY, 
     employeeID  VARCHAR(255), 
     visitDate VARCHAR(255),
	 saleNumber INT
  );
  CREATE TABLE customerOrder
  ( 
     saleNumber INT NOT NULL PRIMARY KEY, 
     menuID VARCHAR(255),
	 inventoryID VARCHAR(255)
  );
  
ALTER TABLE employee ADD CONSTRAINT fk_employee
FOREIGN KEY (positionID) REFERENCES position (positionID);

ALTER TABLE customer ADD CONSTRAINT fk_customer
FOREIGN KEY (saleNumber) REFERENCES sale (saleNumber);

ALTER TABLE customerOrder ADD CONSTRAINT fk_customerOrder1
FOREIGN KEY (saleNumber) REFERENCES sale (saleNumber);

ALTER TABLE customerOrder ADD CONSTRAINT fk_customerOrder2
FOREIGN KEY (menuID) REFERENCES menu_item (menuID);

ALTER TABLE food ADD CONSTRAINT fk_food
FOREIGN KEY (menuID) REFERENCES menu_item (menuID);

ALTER TABLE drink ADD CONSTRAINT fk_drink
FOREIGN KEY (menuID) REFERENCES menu_item (menuID);

ALTER TABLE cocktailRecipe ADD CONSTRAINT fk_cocktailRecipe
FOREIGN KEY (menuID) REFERENCES menu_item (menuID);

	 /*-- sample data*/
	INSERT INTO menu_item ( menuID ,price )
	VALUES ( '1a' ,
			 10.25 );
	INSERT INTO menu_item ( menuID ,price )
	VALUES ( '1b' ,
			 5.00 );	
	INSERT INTO menu_item ( menuID ,price )
	VALUES ( '10a' ,
			 6.00 );
	INSERT INTO menu_item ( menuID ,price )
	VALUES ( '11a' ,
			 4.00 );
			 
	INSERT INTO food ( foodID ,name, category, menuID )
	VALUES ( '10' ,
			 'cheeseburger', 'burgers', '1b');
	INSERT INTO food ( foodID ,name, category, menuID )
	VALUES ( '11' ,
			 'steak', 'entrees', '1a');
	INSERT INTO drink ( drinkID ,name, category, menuID )
	VALUES ( '100' ,
			 'martini', 'cocktails', '10a');
	INSERT INTO drink ( drinkID ,name, category, menuID )
	VALUES ( '101' ,
			 'margarita', 'cocktails', '11a');
	INSERT INTO inventory ( inventoryID ,name, stockQuantity, vendor )
	VALUES ( '1001a' ,
			 'salt', '20 lb', 'Morton');
	INSERT INTO ingredient ( ingredientID , amount, inventoryID, menuID )
	VALUES ( '1001a' ,
			 '1 oz', '1001a', '11a');		
	INSERT INTO position	( positionID , payRate )
	VALUES ( 'server' ,
			 3.00);		 
	INSERT INTO employee ( employeeID , name, positionID )
	VALUES ( '909561' ,
			 'Bret Micheals', 'server');	
	INSERT INTO cocktailRecipe ( drinkID , name, ingredient, amount, menuID )
	VALUES ( '100' ,
			 'martini', 'vermouth', '2 oz', '10a');			 
	INSERT INTO sale (saleNumber, total) 
	VALUES (1, 55.60);
	INSERT INTO customer ( customerID , name, creditCardNo, saleNumber )
	VALUES ( 10101521 ,
			 'Jon Snow', 1234567897654321, 1);	
	INSERT INTO seating ( tableID , employeeID, visitDate, saleNumber )
	VALUES ( 'S5-A' ,
			 '909561', '13-NOV-14', 1 );
	INSERT INTO customerOrder ( saleNumber , menuID, inventoryID )
	VALUES ( 1 ,
			 '1a', '1001a');