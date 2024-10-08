CREATE TABLE public.permissions (
    id bigint NOT NULL,
    name character varying(20) NOT NULL
);

ALTER TABLE public.permissions OWNER TO admin;

ALTER TABLE public.permissions ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.permissions_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);

COPY public.permissions (id, name) FROM stdin;
\.

SELECT pg_catalog.setval('public.permissions_id_seq', 1, false);

ALTER TABLE ONLY public.permissions
    ADD CONSTRAINT permissions_name_key UNIQUE (name);

ALTER TABLE ONLY public.permissions
    ADD CONSTRAINT permissions_pkey PRIMARY KEY (id);