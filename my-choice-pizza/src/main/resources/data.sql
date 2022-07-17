-- user roles
INSERT INTO roles (id, role)
VALUES (1, 'ADMIN');
INSERT INTO roles (id, role)
VALUES (2, 'USER');

--categories
INSERT INTO categories (id, name)
VALUES (1, 'КОЛБАСИ');
INSERT INTO categories (id, name)
VALUES (2, 'МЛЕЧНИ_И_ЯЙЦЕ');
INSERT INTO categories (id, name)
VALUES (3, 'ЗЕЛЕНЧУЦИ');

--ingredients
INSERT INTO ingredients (id, name, category_id)
VALUES (1, 'червени печени чушки', 3);
INSERT INTO ingredients (id, name, category_id)
VALUES (2, 'гъби', 3);
INSERT INTO ingredients (id, name, category_id)
VALUES (3, 'маслини', 3);
INSERT INTO ingredients (id, name, category_id)
VALUES (4, 'домати', 3);
INSERT INTO ingredients (id, name, category_id)
VALUES (5, 'кисели краставички', 3);
INSERT INTO ingredients (id, name, category_id)
VALUES (6, 'зелени чушки', 3);
INSERT INTO ingredients (id, name, category_id)
VALUES (7, 'броколи', 3);
INSERT INTO ingredients (id, name, category_id)
VALUES (8, 'червен лук', 3);
INSERT INTO ingredients (id, name, category_id)
VALUES (9, 'шунка', 1);
INSERT INTO ingredients (id, name, category_id)
VALUES (10, 'луканка', 1);
INSERT INTO ingredients (id, name, category_id)
VALUES (11, 'пилешко филе', 1);
INSERT INTO ingredients (id, name, category_id)
VALUES (12, 'пеперони', 1);
INSERT INTO ingredients (id, name, category_id)
VALUES (13, 'прошуто', 1);
INSERT INTO ingredients (id, name, category_id)
VALUES (14, 'риба тон', 1);
INSERT INTO ingredients (id, name, category_id)
VALUES (15, 'бекон', 1);
INSERT INTO ingredients (id, name, category_id)
VALUES (16, 'кашкавал', 2);
INSERT INTO ingredients (id, name, category_id)
VALUES (17, 'бяло сирене', 2);
INSERT INTO ingredients (id, name, category_id)
VALUES (18, 'синьо сирене', 2);
INSERT INTO ingredients (id, name, category_id)
VALUES (19, 'моцарела', 2);
INSERT INTO ingredients (id, name, category_id)
VALUES (20, 'пармезан', 2);
INSERT INTO ingredients (id, name, category_id)
VALUES (21, 'топено сирене', 2);
INSERT INTO ingredients (id, name, category_id)
VALUES (22, 'яйце', 2);

--pictures
INSERT INTO pictures(public_id, title, url, ingredient_id)
VALUES ('pecheni-chuski_kcrr1x', 'червени чушки',  'https://res.cloudinary.com/dlvuxjqtu/image/upload/v1657966342/pecheni-chuski_kcrr1x.jpg', 1);
INSERT INTO pictures(public_id, title, url, ingredient_id)
VALUES('becon_aitnny', 'бекон', 'https://res.cloudinary.com/dlvuxjqtu/image/upload/v1657548756/becon_aitnny.jpg', 15);
INSERT INTO pictures(public_id, title, url, ingredient_id)
VALUES('proshuto_scvejz', 'прошуто', 'https://res.cloudinary.com/dlvuxjqtu/image/upload/v1657548757/proshuto_scvejz.jpg', 13);
INSERT INTO pictures(public_id, title, url, ingredient_id)
VALUES('pepperoni_ppcikt', 'пеперони', 'https://res.cloudinary.com/dlvuxjqtu/image/upload/v1657548757/pepperoni_ppcikt.jpg', 12);
INSERT INTO pictures(public_id, title, url, ingredient_id)
VALUES('ham_t5yaip', 'шунка', 'https://res.cloudinary.com/dlvuxjqtu/image/upload/v1657966362/ham_t5yaip.jpg', 9);
INSERT INTO pictures(public_id, title, url, ingredient_id)
VALUES('lukanka_hqofwp', 'луканка', 'https://res.cloudinary.com/dlvuxjqtu/image/upload/v1657548756/lukanka_hqofwp.jpg', 10);
INSERT INTO pictures(public_id, title, url, ingredient_id)
VALUES('chicken-slices_cq7noq', 'пилешко филе', 'https://res.cloudinary.com/dlvuxjqtu/image/upload/v1657548756/chicken-slices_cq7noq.jpg', 11);
INSERT INTO pictures(public_id, title, url, ingredient_id)
VALUES('tuna_lz0dyb', 'риба тон', 'https://res.cloudinary.com/dlvuxjqtu/image/upload/v1657548757/tuna_lz0dyb.jpg', 14);
INSERT INTO pictures(public_id, title, url, ingredient_id)
VALUES('egg_u9qo8c', 'яйце', 'https://res.cloudinary.com/dlvuxjqtu/image/upload/v1657548793/egg_u9qo8c.jpg', 22);
INSERT INTO pictures(public_id, title, url, ingredient_id)
VALUES('blue-cheese_bvcxem', 'синьо сирене', 'https://res.cloudinary.com/dlvuxjqtu/image/upload/v1657548793/blue-cheese_bvcxem.jpg', 18);
INSERT INTO pictures(public_id, title, url, ingredient_id)
VALUES('sirene-plato_nkhojy', 'бяло сирене', 'https://res.cloudinary.com/dlvuxjqtu/image/upload/v1657548793/sirene-plato_nkhojy.jpg', 17);
INSERT INTO pictures(public_id, title, url, ingredient_id)
VALUES('parmesan_lxldz3', 'пармезан', 'https://res.cloudinary.com/dlvuxjqtu/image/upload/v1657548793/parmesan_lxldz3.jpg', 20);
INSERT INTO pictures(public_id, title, url, ingredient_id)
VALUES('smoked-cheese_lkbyze', 'топено сирене', 'https://res.cloudinary.com/dlvuxjqtu/image/upload/v1657548793/smoked-cheese_lkbyze.jpg', 21);
INSERT INTO pictures(public_id, title, url, ingredient_id)
VALUES('kashkaval_oa3ylv', 'кашкавал', 'https://res.cloudinary.com/dlvuxjqtu/image/upload/v1657548793/kashkaval_oa3ylv.png', 16);
INSERT INTO pictures(public_id, title, url, ingredient_id)
VALUES('mozarella_o12alv', 'моцарела', 'https://res.cloudinary.com/dlvuxjqtu/image/upload/v1657548793/mozarella_o12alv.jpg', 19);
INSERT INTO pictures(public_id, title, url, ingredient_id)
VALUES('mushrooms_vvac73', 'гъби', 'https://res.cloudinary.com/dlvuxjqtu/image/upload/v1657551912/mushrooms_vvac73.jpg', 2);
INSERT INTO pictures(public_id, title, url, ingredient_id)
VALUES('green-pepper_yipkef', 'зелени чушки', 'https://res.cloudinary.com/dlvuxjqtu/image/upload/v1657551912/green-pepper_yipkef.jpg', 6);
INSERT INTO pictures(public_id, title, url, ingredient_id)
VALUES('broccoli_dzzdvz', 'броколи', 'https://res.cloudinary.com/dlvuxjqtu/image/upload/v1657551912/broccoli_dzzdvz.jpg', 7);
INSERT INTO pictures(public_id, title, url, ingredient_id)
VALUES('pickles_tm24qg', 'кисели краставички', 'https://res.cloudinary.com/dlvuxjqtu/image/upload/v1657551912/pickles_tm24qg.jpg', 5);
INSERT INTO pictures(public_id, title, url, ingredient_id)
VALUES('onion_xn4rdl', 'червен лук', 'https://res.cloudinary.com/dlvuxjqtu/image/upload/v1657551912/onion_xn4rdl.jpg', 8);
INSERT INTO pictures(public_id, title, url, ingredient_id)
VALUES('maslini_x9alis', 'маслини', 'https://res.cloudinary.com/dlvuxjqtu/image/upload/v1657551913/maslini_x9alis.jpg', 3);
INSERT INTO pictures(public_id, title, url, ingredient_id)
VALUES('tomatoes_jtue0t', 'домати', 'https://res.cloudinary.com/dlvuxjqtu/image/upload/v1657551913/tomatoes_jtue0t.jpg', 4);

--prices
INSERT INTO prices(id, pizza_size, additional_product_price, base_price)
VALUES (1, 'МАЛКА', 0.50, 7.70);
INSERT INTO prices(id, pizza_size, additional_product_price, base_price)
VALUES (2, 'СРЕДНА', 0.70, 9.80);
INSERT INTO prices(id, pizza_size, additional_product_price, base_price)
VALUES (3, 'ГОЛЯМА', 1.00, 14.60);
INSERT INTO prices(id, pizza_size, additional_product_price, base_price)
VALUES (4, 'ПАРТИ', 1.60, 19.80);