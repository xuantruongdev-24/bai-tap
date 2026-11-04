USE NORTHWND;
GO
SELECT[ProductName],[UnitPrice],[QuantityPerUnit]
FROM[dbo].[Products]
SELECT[ContactName],[Country]
FROM[dbo].[Customers]
SELECT CompanyName,Phone
FROM Suppliers
SELECT *
FROM Products
SELECT DISTINCT Country
FROM Customers
SELECT DISTINCT PostalCode
FROM Suppliers
SELECT DISTINCT LastName,TitleOfCourtesy
FROM Employees
SELECT DISTINCT SHIPVIA
FROM Orders
SELECT TOP 30 PERCENT *
FROM Employees
SELECT DISTINCT TOP 5 CustomerID
FROM Orders 
SELECT CompanyName AS [TÊN CÔNG TY],PostalCode AS [MÃ BƯU ĐIỆN]
FROM[dbo].[Customers]
SELECT CompanyName AS "TÊN CÔNG TY"
FROM Customers
SELECT TOP 15 O. *
FROM Orders AS "O"
SELECT TOP 5 P.ProductName AS [TÊN SẢN PHẨM],P.SupplierID AS [MÃ NHÀ CUNG CẤP],P.CategoryID AS [MÃ THỂ LOẠI]
FROM Products AS [P]
-- VIẾT CÂU LỆNH SQL TÌM GIÁ TRỊ THẤP NHẤT CỦA CÁC SẢN PHẨM TRONG BẢNG
SELECT MIN(UnitPrice) AS MINPRICE
FROM [dbo].[Products]
-- VIẾT CÂU LỆNH LẤY RA NGÀY ĐẶT HÀNG GẦN ĐÂY NHẤT  TỪ BẢNG ORDERS.
SELECT MAX(OrderDate) AS minOrderDay
FROM Orders
SELECT MIN(BirthDate) AS BIRTHDAY
FROM Employees
SELECT COUNT(*) AS [CUSTOMER]
FROM Customers
SELECT SUM(Freight) AS [SUMFREIGHT]
FROM Orders
SELECT AVG(Quantity) AS [QUANTITY]
FROM [Order Details]
SELECT *
FROM [dbo].[Suppliers]
ORDER BY CompanyName ASC
SELECT *
FROM Products
ORDER BY UnitPrice DESC
SELECT *
FROM Employees
ORDER BY LastName,FirstName
SELECT TOP 1 *
FROM [dbo].[Order Details]
ORDER BY Quantity DESC
SELECT	ProductID,
		ProductName,
		(UnitsInStock-UnitsOnOrder) AS [STOCKREAINING]
FROM Products
SELECT *,(UnitPrice * Quantity) AS ORDERDETAILVALUE
FROM [Order Details]
SELECT AVG(Freight)/MAX(Freight) AS FREIGHTRATIO
FROM Orders
SELECT ProductID,UnitPrice
FROM Products
select p.ProductName,p.UnitPrice as [giá gốc], ((p.UnitPrice * 90) / 100) as [giá sau khi giảm 10%] 
from Products as p
SELECT *
FROM Employees
WHERE City = 'LONDON'
ORDER BY LastName ASC
SELECT	OrderID,
		RequiredDate,
		ShippedDate
FROM [dbo].[Orders] 
WHERE [RequiredDate] > [ShippedDate];
SELECT COUNT (*)
FROM [Order Details]
WHERE [Discount] > 0.1
SELECT *
FROM Orders
WHERE ShipCountry ='FRANCE'
SELECT UnitsInStock
FROM Products
WHERE UnitsInStock > 20
SELECT *
FROM Products
WHERE UnitsInStock < 50 OR UnitsInStock >100
SELECT *
FROM Products
WHERE UnitPrice < 100 and CategoryID > 1
select *
from Orders
where Freight > 50 and Freight < 100
select *
from Products
where UnitsInStock > 20 and  UnitsOnOrder < 20
select *
from Orders
where OrderDate between '1996-07-01' and '1996-07-31'; 











