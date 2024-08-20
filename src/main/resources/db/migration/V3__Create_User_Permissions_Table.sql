CREATE TABLE public.user_permissions (
    permission_id bigint NOT NULL,
    user_id bigint NOT NULL
);

ALTER TABLE public.user_permissions OWNER TO admin;

COPY public.user_permissions (permission_id, user_id) FROM stdin;
\.

ALTER TABLE ONLY public.user_permissions
    ADD CONSTRAINT user_permissions_pkey PRIMARY KEY (permission_id, user_id);

ALTER TABLE ONLY public.user_permissions
    ADD CONSTRAINT fkkowxl8b2bngrxd1gafh13005u FOREIGN KEY (user_id) REFERENCES public.users(id);

ALTER TABLE ONLY public.user_permissions
    ADD CONSTRAINT fkq4qlrabt4s0etm9tfkoqfuib1 FOREIGN KEY (permission_id) REFERENCES public.permissions(id);