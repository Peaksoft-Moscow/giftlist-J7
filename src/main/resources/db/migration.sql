INSERT INTO users(role, shoes_size, clothes_size, country, social_media, birthday, email, hobby, important,
                  last_name, name,
                  password, phone_number, subscribe,active,activation_code)
VALUES ( 'ADMIN', 'THIRTY_SIX', 'S', 'KYRGYZSTAN', 'INSTAGRAM', '1994.02.17', 'admin@gmail.com', 'reading', 'naz',
        'Mansuralieva', 'Nazgul',
        '$2a$12$qzI8tcK2DDUI5PkTvPYeSewtOvqoFHyqsfh06niRkTFoOGZ8gTATe',--123 password
        '89999009090', true,false,null),
       ( 'USER', 'THIRTY_SEVEN', 'M', 'RUSSIA', 'FACEBOOK', '2000.02.02', 'asan@gmail.com', 'dancing', 'asan',
        'Asanov', 'Asan', '$2a$12$pf5ZBE5aSrUlOsgyXNVyCeAN18/RHyEq/8lDrBZCXVCs3z0oZFbRa',--444 password
        '88002223344', false,false,null),
       ( 'USER', 'THIRTY_EIGHT', 'XS', 'KAZAKHSTAN', 'VK', '1999.09.08', 'user@gmail.com', 'singing', 'jamal',
        'Shamuratovna', 'Jamal', '$2a$12$ppzJUl9qIJttL2tIJuhvFOEx6ruvCw9xeygyv0AZTYDV6oXadQvgy',--555 password
        '89001110033', true,false,null);

INSERT INTO categories(id, name)
VALUES (1, 'Electronic'),
       (2, 'Clothes'),
       (3, 'School_requirements'),
       (4, 'House_And_Garden'),
       (5, 'Shoes'),
       (6, 'Transport');

INSERT INTO subcategories(id, name, category_id)
VALUES (1, 'LAPTOP', 1),
       (2, 'COMPUTER', 1),
       (3, 'TABLET', 1),
       (4, 'TV', 1),
       (5, 'AUDIO_EQUIPMENT', 1),
       (6, 'PHOTO_VIDEO_CAMERA', 1),
       (7, 'SMARTPHONE', 1),
       (8, 'CAR_ELECTRONIC', 1),
       (9, 'T_SHIRT', 2),
       (10, 'JACKET', 2),
       (11, 'TROUSERS', 2),
       (12, 'PEN', 3),
       (13, 'PENCIL', 3),
       (14, 'PENCIL_CASE', 3),
       (15, 'BOARD', 3),
       (16, 'COPY_BOOK', 3),
       (17, 'SHOWER', 4),
       (18, 'RAKE', 4),
       (19, 'CARPET', 4),
       (20, 'CURTAINS', 4),
       (21, 'WARDROBE', 4),
       (22, 'PLANT', 4),
       (23, 'BOOTS', 5),
       (24, 'SNEAKERS', 5),
       (25, 'SLIPPER', 5),
       (26, 'SANDALS', 5),
       (27, 'BUS', 6),
       (28, 'BIKE', 6),
       (29, 'CAR', 6),
       (30, 'SCOOTER', 6);

INSERT INTO my_holidays( user_id, description,name)
VALUES (1, 'be happy', 'Mothers_Day');

INSERT INTO bookings(name, create_date, booking_status, user_id)
VALUES ('gifts', '2023.12.20', 'UNBOOKED', 1);

INSERT INTO gifts(add_date, booking_status, condition, date_of_holiday, gift_status, booking_id, category_id,
                  user_id, description, name,holiday_id)
VALUES ('2023.12.29', 'BOOKED', 'NEW', '2023.12.31', 'WISHLIST', 1, 1, 1, 'gift to my friend', 'Telephone', 1);

INSERT INTO mailings(create_date, text, theme)
VALUES ('2023.12.12', 'send gift to your friend', 'gifts');






