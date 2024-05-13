--liquibase formatted sql

--changeset yurieva:001-created-table-tuser
--createdOn: May 5, 2024
--comment: Creates tuser table.
--rollback DROP TABLE IF EXISTS public.tuser;
CREATE TABLE IF NOT EXISTS public.tuser (
    id bigint NOT NULL,
    login "char"[],
    password character varying(255),
    first_name character varying(255),
    last_name character varying(255),
    user_avatar character varying(255),
    role character varying(255),
    removed time without time zone,
    date_birth time without time zone,
    registred timestamp without time zone,
    PRIMARY KEY (id)
    );

--changeset yurieva:002-created-table-artist
--createdOn: May 5, 2024
--comment: Creates artist table.
--rollback DROP TABLE IF EXISTS public.artist;
CREATE TABLE IF NOT EXISTS public.artist (
    id bigint NOT NULL,
    user_id bigint NOT NULL,
    info text,
    country character varying(255),
    city character varying(255),
    total_rating double precision,
    PRIMARY KEY (id)
    );

--changeset yurieva:003-created-table-artwork
--createdOn: May 5, 2024
--comment: Creates artwork table.
--rollback DROP TABLE IF EXISTS public.artwork;
CREATE TABLE IF NOT EXISTS public.artwork (
    id bigint NOT NULL,
    artist_id bigint NOT NULL,
    title character varying(255),
    description text,
    recommendation text,
    date_creation date,
    date_addition date,
    status character varying(255),
    original boolean,
    possible_remake boolean,
    frame boolean,
    size_height integer,
    size_width integer,
    size_depth integer,
    url_image character varying(255),
    PRIMARY KEY (id)
    );

--changeset yurieva:004-created-table-cart
--createdOn: May 5, 2024
--comment: Creates cart table.
--rollback DROP TABLE IF EXISTS public.cart;
CREATE TABLE IF NOT EXISTS public.cart (
    id bigint NOT NULL,
    user_id bigint NOT NULL,
    total_cost double precision NOT NULL DEFAULT 0,
    PRIMARY KEY (id)
    );

--changeset yurieva:005-created-table-cart_items
--createdOn: May 5, 2024
--comment: Creates cart_items table.
--rollback DROP TABLE IF EXISTS public.cart_items;
CREATE TABLE IF NOT EXISTS public.cart_items
(
    cart_id bigint NOT NULL,
    artwork_id bigint NOT NULL,
    quantity integer NOT NULL DEFAULT 1
);

--changeset yurieva:006-created-table-order
--createdOn: May 5, 2024
--comment: Creates order table.
--rollback DROP TABLE IF EXISTS public."order";
CREATE TABLE IF NOT EXISTS public."order" (
    id bigint NOT NULL,
    user_id bigint NOT NULL,
    order_date_time timestamp without time zone,
    status character varying(255),
    total_cost double precision,
    payment_method character varying(255),
    payment_status character varying(255),
    PRIMARY KEY (id)
    );

--changeset yurieva:007-created-table-order_items
--createdOn: May 5, 2024
--comment: Creates order_items table.
--rollback DROP TABLE IF EXISTS public.order_items;
CREATE TABLE IF NOT EXISTS public.order_items
(
    order_id bigint NOT NULL,
    artwork_id bigint NOT NULL,
    quantity integer NOT NULL DEFAULT 1
);

--changeset yurieva:008-created-table-styles
--createdOn: May 5, 2024
--comment: Creates styles table.
--rollback DROP TABLE IF EXISTS public.styles;
CREATE TABLE IF NOT EXISTS public.styles (
    id bigint NOT NULL,
    title character varying(255),
    description text,
    PRIMARY KEY (id)
    );

--changeset yurieva:009-created-table-materials
--createdOn: May 5, 2024
--comment: Creates materials table.
--rollback DROP TABLE IF EXISTS public.materials;
CREATE TABLE IF NOT EXISTS public.materials (
    id bigint NOT NULL,
    title character varying(255),
    description text,
    PRIMARY KEY (id)
    );

--changeset yurieva:010-created-table-techniques
--createdOn: May 5, 2024
--comment: Creates techniques table.
--rollback DROP TABLE IF EXISTS public.techniques;
CREATE TABLE IF NOT EXISTS public.techniques (
    id bigint NOT NULL,
    title character varying,
    description text,
    PRIMARY KEY (id)
    );

--changeset yurieva:011-created-table-artwork_price_list
--createdOn: May 5, 2024
--comment: Creates artwork_price_list table.
--rollback DROP TABLE IF EXISTS public.artwork_price_list;
CREATE TABLE IF NOT EXISTS public.artwork_price_list (
    id bigint NOT NULL,
    artwork_id bigint NOT NULL,
    price double precision,
    discount double precision,
    PRIMARY KEY (id)
    );

--changeset yurieva:012-created-table-artwork_materials
--createdOn: May 5, 2024
--comment: Creates artwork_materials table.
--rollback DROP TABLE IF EXISTS public.artwork_materials;
CREATE TABLE IF NOT EXISTS public.artwork_materials (
    artwork_id bigint NOT NULL,
    materials_id bigint NOT NULL
);

--changeset yurieva:013-created-table-artwork_styles
--createdOn: May 5, 2024
--comment: Creates artwork_styles table.
--rollback DROP TABLE IF EXISTS public.artwork_styles;
CREATE TABLE IF NOT EXISTS public.artwork_styles (
    styles_id bigint NOT NULL,
    artwork_id bigint NOT NULL
);

--changeset yurieva:014-created-table-artwork_techniques
--createdOn: May 5, 2024
--comment: Creates artwork_techniques table.
--rollback DROP TABLE IF EXISTS public.artwork_techniques;
CREATE TABLE IF NOT EXISTS public.artwork_techniques (
    techniques_id bigint NOT NULL,
    artwork_id bigint NOT NULL
);

--changeset yurieva:015-created-table-collection
--createdOn: May 5, 2024
--comment: Creates collection table.
--rollback DROP TABLE IF EXISTS public.collection;
CREATE TABLE IF NOT EXISTS public.collection (
    id bigint NOT NULL,
    user_id bigint NOT NULL,
    title character varying(255) NOT NULL,
    description text,
    PRIMARY KEY (id)
    );

--changeset yurieva:016-created-table-collection_artwork
--createdOn: May 5, 2024
--comment: Creates collection_artwork table.
--rollback DROP TABLE IF EXISTS public.collection_artwork;
CREATE TABLE IF NOT EXISTS public.collection_artwork (
    collection_id bigint NOT NULL,
    artwork_id bigint NOT NULL
);

--changeset yurieva:017-created-table-review
--createdOn: May 5, 2024
--comment: Creates review table.
--rollback DROP TABLE IF EXISTS public.review;
CREATE TABLE IF NOT EXISTS public.review (
    id bigint NOT NULL,
    user_id bigint NOT NULL,
    artwork_id bigint NOT NULL,
    rating integer NOT NULL,
    comment character varying(255),
    PRIMARY KEY (id)
    );

--changeset yurieva:018-created-table-response_review
--createdOn: May 5, 2024
--comment: Creates response_review table.
--rollback DROP TABLE IF EXISTS public.response_review;
CREATE TABLE IF NOT EXISTS public.response_review (
    id bigint NOT NULL,
    user_id bigint NOT NULL,
    review_id bigint NOT NULL,
    comment character varying(255) NOT NULL,
    PRIMARY KEY (id)
    );

--changeset yurieva:019-add-foreign-key-artist-user_id
--createdOn: May 5, 2024
--comment: Adds foreign key constraint from artist to tuser.
ALTER TABLE IF EXISTS public.artist
    ADD FOREIGN KEY (user_id)
    REFERENCES public.tuser (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;

--changeset yurieva:020-add-foreign-key-artwork-artist_id
--createdOn: May 5, 2024
--comment: Adds foreign key constraint from artwork to artist.
ALTER TABLE IF EXISTS public.artwork
    ADD FOREIGN KEY (artist_id)
    REFERENCES public.artist (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;

--changeset yurieva:021-add-foreign-key-cart-cart_items
--createdOn: May 5, 2024
--comment: Adds foreign key constraint from cart to cart_items.
ALTER TABLE IF EXISTS public.cart_items
    ADD FOREIGN KEY (cart_id)
    REFERENCES public.cart (id) MATCH SIMPLE
    ON UPDATE NO ACTION
       ON DELETE NO ACTION
    NOT VALID;

--changeset yurieva:022-add-foreign-key-artwork-cart_items
--createdOn: May 5, 2024
--comment: Adds foreign key constraint from artwork to cart_items.
ALTER TABLE IF EXISTS public.cart_items
    ADD FOREIGN KEY (artwork_id)
    REFERENCES public.artwork (id) MATCH SIMPLE
    ON UPDATE NO ACTION
       ON DELETE NO ACTION
    NOT VALID;

--changeset yurieva:023-add-foreign-key-order-order_items
--createdOn: May 5, 2024
--comment: Adds foreign key constraint from order to order_items.
ALTER TABLE IF EXISTS public.order_items
    ADD FOREIGN KEY (order_id)
    REFERENCES public."order" (id) MATCH SIMPLE
    ON UPDATE NO ACTION
       ON DELETE NO ACTION
    NOT VALID;

--changeset yurieva:024-add-foreign-key-artwork-order_items
--createdOn: May 5, 2024
--comment: Adds foreign key constraint from artwork to order_items.
ALTER TABLE IF EXISTS public.order_items
    ADD FOREIGN KEY (artwork_id)
    REFERENCES public.artwork (id) MATCH SIMPLE
    ON UPDATE NO ACTION
       ON DELETE NO ACTION
    NOT VALID;

--changeset yurieva:025-add-foreign-key-cart-user_id
--createdOn: May 5, 2024
--comment: Adds foreign key constraint from cart to tuser.
ALTER TABLE IF EXISTS public.cart
    ADD FOREIGN KEY (user_id)
    REFERENCES public.tuser (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;

--changeset yurieva:026-add-foreign-key-cart_items-cart_id
--createdOn: May 5, 2024
--comment: Adds foreign key constraint from cart_items to cart.
ALTER TABLE IF EXISTS public.cart_items
    ADD FOREIGN KEY (cart_id)
    REFERENCES public.cart (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;

--changeset yurieva:027-add-foreign-key-order-user_id
--createdOn: May 5, 2024
--comment: Adds foreign key constraint from order to tuser.
ALTER TABLE IF EXISTS public."order"
    ADD FOREIGN KEY (user_id)
    REFERENCES public.tuser (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;

--changeset yurieva:028-add-foreign-key-order_items-order_id
--createdOn: May 5, 2024
--comment: Adds foreign key constraint from order_items to order.
ALTER TABLE IF EXISTS public.order_items
    ADD FOREIGN KEY (order_id)
    REFERENCES public."order" (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;

--changeset yurieva:029-add-foreign-key-artwork_price_list-artwork_id
--createdOn: May 5, 2024
--comment: Adds foreign key constraint from artwork_price_list to artwork.
ALTER TABLE IF EXISTS public.artwork_price_list
    ADD FOREIGN KEY (artwork_id)
    REFERENCES public.artwork (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;

--changeset yurieva:030-add-foreign-key-artwork_materials-artwork_id
--createdOn: May 5, 2024
--comment: Adds foreign key constraint from artwork_materials to artwork.
ALTER TABLE IF EXISTS public.artwork_materials
    ADD FOREIGN KEY (artwork_id)
    REFERENCES public.artwork (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;

--changeset yurieva:031-add-foreign-key-artwork_materials-materials_id
--createdOn: May 5, 2024
--comment: Adds foreign key constraint from artwork_materials to materials.
ALTER TABLE IF EXISTS public.artwork_materials
    ADD FOREIGN KEY (materials_id)
    REFERENCES public.materials (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;

--changeset yurieva:032-add-foreign-key-artwork_styles-styles_id
--createdOn: May 5, 2024
--comment: Adds foreign key constraint from artwork_styles to styles.
ALTER TABLE IF EXISTS public.artwork_styles
    ADD FOREIGN KEY (styles_id)
    REFERENCES public.styles (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;

--changeset yurieva:033-add-foreign-key-artwork_styles-artwork_id
--createdOn: May 5, 2024
--comment: Adds foreign key constraint from artwork_styles to artwork.
ALTER TABLE IF EXISTS public.artwork_styles
    ADD FOREIGN KEY (artwork_id)
    REFERENCES public.artwork (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;

--changeset yurieva:034-add-foreign-key-artwork_techniques-techniques_id
--createdOn: May 5, 2024
--comment: Adds foreign key constraint from artwork_techniques to techniques.
ALTER TABLE IF EXISTS public.artwork_techniques
    ADD FOREIGN KEY (techniques_id)
    REFERENCES public.techniques (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;

--changeset yurieva:035-add-foreign-key-artwork_techniques-artwork_id
--createdOn: May 5, 2024
--comment: Adds foreign key constraint from artwork_techniques to artwork.
ALTER TABLE IF EXISTS public.artwork_techniques
    ADD FOREIGN KEY (artwork_id)
    REFERENCES public.artwork (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;

--changeset yurieva:036-add-foreign-key-collection-user_id
--createdOn: May 5, 2024
--comment: Adds foreign key constraint from collection to tuser.
ALTER TABLE IF EXISTS public.collection
    ADD FOREIGN KEY (user_id)
    REFERENCES public.tuser (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;

--changeset yurieva:037-add-foreign-key-collection_artwork-collection_id
--createdOn: May 5, 2024
--comment: Adds foreign key constraint from collection_artwork to collection.
ALTER TABLE IF EXISTS public.collection_artwork
    ADD FOREIGN KEY (collection_id)
    REFERENCES public.collection (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;

--changeset yurieva:038-add-foreign-key-collection_artwork-artwork_id
--createdOn: May 5, 2024
--comment: Adds foreign key constraint from collection_artwork to artwork.
ALTER TABLE IF EXISTS public.collection_artwork
    ADD FOREIGN KEY (artwork_id)
    REFERENCES public.artwork (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;

--changeset yurieva:039-add-foreign-key-review-user_id
--createdOn: May 5, 2024
--comment: Adds foreign key constraint from review to tuser.
ALTER TABLE IF EXISTS public.review
    ADD FOREIGN KEY (user_id)
    REFERENCES public.tuser (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;

--changeset yurieva:040-add-foreign-key-review-artwork_id
--createdOn: May 5, 2024
--comment: Adds foreign key constraint from review to artwork.
ALTER TABLE IF EXISTS public.review
    ADD FOREIGN KEY (artwork_id)
    REFERENCES public.artwork (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;

--changeset yurieva:041-add-foreign-key-response_review-user_id
--createdOn: May 5, 2024
--comment: Adds foreign key constraint from response_review to tuser.
ALTER TABLE IF EXISTS public.response_review
    ADD FOREIGN KEY (user_id)
    REFERENCES public.tuser (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;

--changeset yurieva:042-add-foreign-key-response_review-review_id
--createdOn: May 5, 2024
--comment: Adds foreign key constraint from response_review to review.
ALTER TABLE IF EXISTS public.response_review
    ADD FOREIGN KEY (review_id)
    REFERENCES public.review (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;
