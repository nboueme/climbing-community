
CREATE SEQUENCE public.user_account_id_seq;

CREATE TABLE public.User_Account (
                id INTEGER NOT NULL DEFAULT nextval('public.user_account_id_seq'),
                pseudo VARCHAR(20) NOT NULL,
                email VARCHAR(100) NOT NULL,
                password VARCHAR(100) NOT NULL,
                image_url VARCHAR(200),
                role VARCHAR(5) DEFAULT 'user' NOT NULL,
                created_at TIMESTAMP(0) DEFAULT current_timestamp NOT NULL,
                updated_at TIMESTAMP(0),
                CONSTRAINT user_account_pk PRIMARY KEY (id)
);
COMMENT ON COLUMN public.User_Account.role IS '"user" or "admin"';


ALTER SEQUENCE public.user_account_id_seq OWNED BY public.User_Account.id;

CREATE UNIQUE INDEX user_account_pseudo_uindex
 ON public.User_Account
 ( pseudo ASC );

CREATE UNIQUE INDEX user_account_email_uindex
 ON public.User_Account
 ( email ASC );

CREATE SEQUENCE public.publication_id_seq;

CREATE TABLE public.Publication (
                id INTEGER NOT NULL DEFAULT nextval('public.publication_id_seq'),
                user_account_id INTEGER NOT NULL,
                name VARCHAR(100) NOT NULL,
                created_at TIMESTAMP(0) DEFAULT current_timestamp NOT NULL,
                updated_at TIMESTAMP(0),
                CONSTRAINT publication_pk PRIMARY KEY (id)
);


ALTER SEQUENCE public.publication_id_seq OWNED BY public.Publication.id;

CREATE TABLE public.Topo (
                publication_id INTEGER NOT NULL,
                description VARCHAR(2000),
                image_url VARCHAR(200),
                CONSTRAINT topo_pk PRIMARY KEY (publication_id)
);


CREATE TABLE public.User_Has_Topo (
                user_id INTEGER NOT NULL,
                topo_id INTEGER NOT NULL,
                is_loaned BOOLEAN NOT NULL,
                borrowing_date DATE,
                return_date DATE,
                CONSTRAINT user_has_topo_pk PRIMARY KEY (user_id, topo_id)
);


CREATE TABLE public.Spot (
                publication_id INTEGER NOT NULL,
                description VARCHAR(2000),
                height INTEGER,
                CONSTRAINT spot_pk PRIMARY KEY (publication_id)
);


CREATE TABLE public.Topo_Has_Spot (
                topo_id INTEGER NOT NULL,
                spot_id INTEGER NOT NULL,
                CONSTRAINT topo_has_spot_pk PRIMARY KEY (topo_id, spot_id)
);


CREATE TABLE public.Sector (
                publication_id INTEGER NOT NULL,
                spot_id INTEGER NOT NULL,
                height INTEGER,
                CONSTRAINT sector_pk PRIMARY KEY (publication_id)
);


CREATE TABLE public.Route (
                publication_id INTEGER NOT NULL,
                sector_id INTEGER NOT NULL,
                parent_publication_id INTEGER,
                height INTEGER,
                quotation VARCHAR(2) NOT NULL,
                latitude NUMERIC(9,6) NOT NULL,
                longitude NUMERIC(9,6) NOT NULL,
                points_number INTEGER,
                type_route VARCHAR(6) NOT NULL,
                CONSTRAINT route_pk PRIMARY KEY (publication_id)
);
COMMENT ON COLUMN public.Route.type_route IS '"route" or "length"';


CREATE SEQUENCE public.comment_id_seq;

CREATE TABLE public.Comment (
                id INTEGER NOT NULL DEFAULT nextval('public.comment_id_seq'),
                user_account_id INTEGER NOT NULL,
                publication_id INTEGER NOT NULL,
                parent_id INTEGER,
                created_at TIMESTAMP(0) DEFAULT current_timestamp NOT NULL,
                updated_at TIMESTAMP(0),
                content VARCHAR(1000) NOT NULL,
                CONSTRAINT comment_pk PRIMARY KEY (id)
);


ALTER SEQUENCE public.comment_id_seq OWNED BY public.Comment.id;

ALTER TABLE public.Comment ADD CONSTRAINT user_comment_fk
FOREIGN KEY (user_account_id)
REFERENCES public.User_Account (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Publication ADD CONSTRAINT user_publication_fk
FOREIGN KEY (user_account_id)
REFERENCES public.User_Account (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.User_Has_Topo ADD CONSTRAINT useraccount_userhastopo_fk
FOREIGN KEY (user_id)
REFERENCES public.User_Account (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Sector ADD CONSTRAINT publication_sector_fk
FOREIGN KEY (publication_id)
REFERENCES public.Publication (id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Route ADD CONSTRAINT publication_route_fk
FOREIGN KEY (publication_id)
REFERENCES public.Publication (id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Spot ADD CONSTRAINT publication_spot_fk
FOREIGN KEY (publication_id)
REFERENCES public.Publication (id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Topo ADD CONSTRAINT publication_topo_fk
FOREIGN KEY (publication_id)
REFERENCES public.Publication (id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Comment ADD CONSTRAINT publication_comment_fk
FOREIGN KEY (publication_id)
REFERENCES public.Publication (id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.User_Has_Topo ADD CONSTRAINT topo_userhastopo_fk
FOREIGN KEY (topo_id)
REFERENCES public.Topo (publication_id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Topo_Has_Spot ADD CONSTRAINT topo_topohasspot_fk
FOREIGN KEY (topo_id)
REFERENCES public.Topo (publication_id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Topo_Has_Spot ADD CONSTRAINT spot_topohasspot_fk
FOREIGN KEY (spot_id)
REFERENCES public.Spot (publication_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Sector ADD CONSTRAINT spot_sector_fk
FOREIGN KEY (spot_id)
REFERENCES public.Spot (publication_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Route ADD CONSTRAINT sector_route_fk
FOREIGN KEY (sector_id)
REFERENCES public.Sector (publication_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Route ADD CONSTRAINT route_route_fk
FOREIGN KEY (parent_publication_id)
REFERENCES public.Route (publication_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Comment ADD CONSTRAINT comment_comment_fk
FOREIGN KEY (parent_id)
REFERENCES public.Comment (id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;
