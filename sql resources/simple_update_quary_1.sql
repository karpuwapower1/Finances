DELETE FROM init
WHERE login='aliaksandr';

SELECT counter_book.login, 
		counter_book.date, 
        counter_book.amount,
        accounts.cart_name
FROM counter_book
JOIN accounts ON counter_book.cart_name=accounts.cart_name
