UPDATE accounts
SET cart_name='salary_cart'
WHERE login='aliaksei' AND cart_name='first_cart';

SELECT* 
FROM counter_book
JOIN accounts ON counter_book.cart_name=accounts.cart_name;