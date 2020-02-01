UPDATE counter_book
SET amount = 0
WHERE amount < 0;

SELECT login, amount, cart_name
FROM counter_book;