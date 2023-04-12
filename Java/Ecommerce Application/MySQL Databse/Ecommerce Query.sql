use z_project_ecommerce;

create table Product_Information(ProductId int not null auto_increment, ProductName varchar(100), ProductDescription varchar(100), ProductPrice int(200),ProductQuantity int(200), primary key (ProductId));

create table Customer_Information(CustomerNo int not null auto_increment, CustomerEmail varchar(100), CustomerPassword varchar(100), CustomerName varchar(100), CustomerAddress varchar(100), CustomerMobile int(100), Cart1Id int(100), Cart1Qnt int(100), Cart2Id int(100), Cart2Qnt int(100), Cart3Id int(100), Cart3Qnt int(100), Cart4Id int(100), Cart4Qnt int(100), Cart5Id int(100), Cart5Qnt int(100), Cart6Id int(100), Cart6Qnt int(100), Cart7Id int(100), Cart7Qnt int(100), Cart8Id int(100), Cart8Qnt int(100), Cart9Id int(100), Cart9Qnt int(100), Cart10Id int(100), Cart10Qnt int(100), PaymentId varchar(100),PaymentPassword int(100), primary key (CustomerNo));

create table Purchase_History(SerialNumber int not null auto_increment, CustomerNo int(10), CustomerEmail varchar(100), CustomerName varchar(100), ProductIdPurchased int(100), ProductNamePurchased varchar(100), QuantityPurchased int(10), ProductPrice int(10), primary key (SerialNumber));
