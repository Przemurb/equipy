INSERT INTO application_user (FIRST_NAME, LAST_NAME, PESEL)
VALUES
    ('Jan', 'Janowski', '1234567890'),
    ('Anna', 'Annowska','0123456789'),
    ('Zbigniew', 'Zbigniewski', '2345678901');

INSERT INTO category (NAME, DESCRIPTION)
VALUES
    ('Laptopy', 'Laptopy, notebooki i inne przenośne komputery'),
    ('Pojazdy', 'Samochody, motorowery, rowery, hulajnogi...'),
    ('Telefony', 'Telefony komórkowe, ich wyposażenie oraz telefony stacjonarne');

INSERT INTO asset (NAME, DESCRIPTION, SERIAL_NUMBER, CATEGORY_ID)
VALUES
    ('Asus MateBook D', '15 calowy laptop, i5, 8GB DDR3, kolor czarny', 'ASMBD198723', 1),
    ('Apple MacBook Pro 2015', '13 calowy laptop, i5, 16GB DDR3, SSD256GB, kolor srebrny', 'MBP15X0925336', 1),
    ('Dell Inspirion 3576', '13 calowy laptop, i7, 8GB DDR4, SSD 512GB, kolor czarny', 'DI3576XO526716', 2),
    ('Samsung A32', 'Telefon komórkowy marki Samsung, model A32, 6", 4/128 RAM, kolor czarny', 'TS77365VV23', 3),
    ('Apple iPhone 8', 'Telefon z zestawem słuchawkowym lightning i ładowarką', 'APL8185652HGT7', 3);

INSERT INTO assignment (START, STOP, ASSET_ID, APPLICATION_USER_ID)
VALUES
    ('2017-10-08 15:00:00', '2018-10-08 15:00:00', 1, 1),
    ('2018-10-09 12:00:00', null, 5, 1),
    ('2018-10-19 10:00:00', null, 3, 2);

