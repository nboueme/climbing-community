INSERT INTO public.comment (id, user_account_id, publication_id, parent_id, created_at, updated_at, content) VALUES (1, 1, 1, null, '2017-10-27 23:41:13', null, 'Super topo !');
INSERT INTO public.comment (id, user_account_id, publication_id, parent_id, created_at, updated_at, content) VALUES (2, 4, 1, 1, '2017-10-27 23:47:29', null, 'Bien dit Gandalf !');
INSERT INTO public.comment (id, user_account_id, publication_id, parent_id, created_at, updated_at, content) VALUES (3, 5, 1, 1, '2017-10-27 23:57:03', null, 'Je ne suis pas du tout d''accord.');

ALTER SEQUENCE comment_id_seq RESTART WITH 4;