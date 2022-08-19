
-- Table: public.users
-- пароль для user'а = 1234
-- пароль для customer'а = qwerty
INSERT INTO public.users(
    id, username, created, updated, status, first_name, last_name, email, password, user_roles)
VALUES
    (1, 'user', '2022-08-18', '2022-08-18', 'ACTIVE', 'Иван', 'Иванов', '', '$2a$04$u8RIl/hXfFljteczc6ilUu472Xke16UDoUvxQsuHfTjzC1QYj8W3S', 1),
    (2, 'customer', '2022-08-18', '2022-08-18', 'ACTIVE', 'Фёдор', 'Фёдоров', '', '$2a$04$5QbCGdM6ERZaX.TGXEvfSu3lSZuUKymkY8W.hIOuCGohQBHQQfv5W', 2);



INSERT INTO public.roles(
    id, created, status, updated, name)
VALUES
    (1,	'2022-08-18 17:00:00',	'ACTIVE',	'2022-08-18 17:00:00',	'ROLE_USER'),
    (2,	'2022-08-18 17:00:00',	'ACTIVE',	'2022-08-18 17:00:00',	'ROLE_ADMIN');


INSERT INTO public.user_roles(
    user_id, role_id)
VALUES
    (1, 1),
    (1, 2),
    (2, 1);



INSERT INTO public.category(
    id, description, name)
VALUES 
    ('91e3c980-fbea-44b6-8d26-8c2ae38e9d7d',	'Www',	'Мебель'),
    ('9f8d1954-d605-484a-8605-d961afe63c83',	'Мягкие вещи',	'Мягкие вещи'),
    ('e0bf3173-c935-44ed-a387-b7ee1ae5b920',	'Qwerty',	'Строительные материалы');



    


INSERT INTO public.product(
    id, date, description, name, photo, price, status, category_id)
VALUES
    ('5b177fd0-d1b9-4ef3-acbd-4907fcf20823',	'2022-08-18 00:00:00',	'Постельное бельё',	'Подушка',		550,	true,	'9f8d1954-d605-484a-8605-d961afe63c83'),
    ('90a10b79-41a5-44e1-85f9-d0d35b548f26',	'2022-08-18 00:00:00',	'Мягкая игрушка для детей',	'Игрушка',		420,	true,	'9f8d1954-d605-484a-8605-d961afe63c83');

