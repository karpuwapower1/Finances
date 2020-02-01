DELETE FROM init
WHERE login='aliaksandr';

SELECT counter_book.amount, accounts.login, accounts.cart_name
FROM counter_book
JOIN accounts ON counter_book.login=accounts.login;