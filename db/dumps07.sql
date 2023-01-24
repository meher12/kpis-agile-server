--
-- PostgreSQL database dump
--

-- Dumped from database version 13.1
-- Dumped by pg_dump version 13.1

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: by_ref_view; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.by_ref_view (
    id bigint NOT NULL,
    ref_project character varying(255),
    ref_sprint character varying(255),
    ref_story character varying(255),
    ref_task character varying(255),
    task_stared_at timestamp without time zone,
    title_project character varying(255),
    title_sprint character varying(255),
    title_story character varying(255),
    title_task character varying(255),
    story_title character varying(255)
);


ALTER TABLE public.by_ref_view OWNER TO postgres;

--
-- Name: projects; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.projects (
    id bigint NOT NULL,
    date_debut date,
    date_fin date,
    description text,
    p_reference character varying(15),
    pupdated_date timestamp without time zone,
    title text,
    totalsp_commitment integer DEFAULT 0,
    totalsp_completed integer DEFAULT 0,
    totalstorypointsinitiallycounts integer DEFAULT 0
);


ALTER TABLE public.projects OWNER TO postgres;

--
-- Name: sprints; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.sprints (
    id bigint NOT NULL,
    more_sp integer DEFAULT 0,
    s_reference character varying(15),
    sdate_debut date,
    sdate_fin date,
    description text,
    title text,
    supdated_date timestamp without time zone,
    work_commitment integer DEFAULT 0,
    work_completed integer DEFAULT 0,
    projet_id bigint NOT NULL
);


ALTER TABLE public.sprints OWNER TO postgres;

--
-- Name: story; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.story (
    id bigint NOT NULL,
    plus_sp integer DEFAULT 0,
    priority bigint,
    sp_completed integer DEFAULT 0,
    st_reference character varying(15),
    description text,
    title text,
    story_point integer DEFAULT 0,
    stupdated_date timestamp without time zone,
    sprint_id bigint NOT NULL
);


ALTER TABLE public.story OWNER TO postgres;

--
-- Name: tasks; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tasks (
    id bigint NOT NULL,
    bugs integer DEFAULT 0,
    estimation integer DEFAULT 0,
    status character varying(255),
    t_reference character varying(15),
    tdate_debut date,
    tdate_fin date,
    description text,
    title text,
    tsupdated_date timestamp without time zone,
    type_task character varying(255),
    story_id bigint NOT NULL
);


ALTER TABLE public.tasks OWNER TO postgres;

--
-- Name: byref_view; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW public.byref_view AS
 SELECT p.p_reference AS refproject,
    p.title AS projetc_name,
    s.s_reference AS refsprint,
    s.title AS sprint_name,
    st.st_reference AS restory,
    st.title AS story_name,
    t.t_reference AS taskref,
    t.title AS task_name,
    t.tdate_debut AS task_startat
   FROM (((public.projects p
     JOIN public.sprints s ON ((s.projet_id = p.id)))
     JOIN public.story st ON ((s.id = st.sprint_id)))
     JOIN public.tasks t ON ((st.id = t.story_id)))
  WHERE ((p.p_reference)::text = 'PUID1EBD9'::text)
  ORDER BY t.tdate_debut;


ALTER TABLE public.byref_view OWNER TO postgres;

--
-- Name: byview_generator; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.byview_generator
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.byview_generator OWNER TO postgres;

--
-- Name: days_sprints; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.days_sprints (
    sprint_id bigint NOT NULL,
    days_array character varying(1000)
);


ALTER TABLE public.days_sprints OWNER TO postgres;

--
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.hibernate_sequence OWNER TO postgres;

--
-- Name: ideall_sprints; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.ideall_sprints (
    sprint_id bigint NOT NULL,
    il_array character varying(1000)
);


ALTER TABLE public.ideall_sprints OWNER TO postgres;

--
-- Name: jcoverage; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.jcoverage (
    id bigint NOT NULL,
    covered real NOT NULL,
    created_at timestamp without time zone,
    missed real NOT NULL,
    percentage real NOT NULL,
    project_ref character varying(255),
    projectname character varying(255),
    totalpercentage real NOT NULL,
    type character varying(255)
);


ALTER TABLE public.jcoverage OWNER TO postgres;

--
-- Name: jcoverage_generator; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.jcoverage_generator
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.jcoverage_generator OWNER TO postgres;

--
-- Name: member_of_project; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.member_of_project (
    project_id bigint NOT NULL,
    email character varying(255)
);


ALTER TABLE public.member_of_project OWNER TO postgres;

--
-- Name: member_of_task; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.member_of_task (
    task_attached_id bigint NOT NULL,
    mail character varying(255)
);


ALTER TABLE public.member_of_task OWNER TO postgres;

--
-- Name: project_generator; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.project_generator
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.project_generator OWNER TO postgres;

--
-- Name: projet_more_sp; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.projet_more_sp (
    projet_id bigint NOT NULL,
    project_more_sp character varying(1000)
);


ALTER TABLE public.projet_more_sp OWNER TO postgres;

--
-- Name: projet_percentage_spc; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.projet_percentage_spc (
    projet_id bigint NOT NULL,
    percentage_spc character varying(1000)
);


ALTER TABLE public.projet_percentage_spc OWNER TO postgres;

--
-- Name: projet_sp_commitment; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.projet_sp_commitment (
    projet_id bigint NOT NULL,
    project_sp_commitment character varying(1000)
);


ALTER TABLE public.projet_sp_commitment OWNER TO postgres;

--
-- Name: projet_sp_completed; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.projet_sp_completed (
    projet_id bigint NOT NULL,
    project_sp_completed character varying(1000)
);


ALTER TABLE public.projet_sp_completed OWNER TO postgres;

--
-- Name: roles; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.roles (
    id integer NOT NULL,
    name character varying(20)
);


ALTER TABLE public.roles OWNER TO postgres;

--
-- Name: sprint_generator; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.sprint_generator
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.sprint_generator OWNER TO postgres;

--
-- Name: story_generator; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.story_generator
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.story_generator OWNER TO postgres;

--
-- Name: task_generator; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.task_generator
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.task_generator OWNER TO postgres;

--
-- Name: user_generator; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.user_generator
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.user_generator OWNER TO postgres;

--
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    id bigint NOT NULL,
    email character varying(255),
    password character varying(255),
    username character varying(255)
);


ALTER TABLE public.users OWNER TO postgres;

--
-- Name: users_projects; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users_projects (
    user_id bigint NOT NULL,
    project_id bigint NOT NULL
);


ALTER TABLE public.users_projects OWNER TO postgres;

--
-- Name: users_roles; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users_roles (
    user_id bigint NOT NULL,
    role_id integer NOT NULL
);


ALTER TABLE public.users_roles OWNER TO postgres;

--
-- Name: users_tasks; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users_tasks (
    users_id bigint NOT NULL,
    tasks_id bigint NOT NULL
);


ALTER TABLE public.users_tasks OWNER TO postgres;

--
-- Name: workedl_sprints; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.workedl_sprints (
    sprint_id bigint NOT NULL,
    workedl_array character varying(1000)
);


ALTER TABLE public.workedl_sprints OWNER TO postgres;

--
-- Data for Name: by_ref_view; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.by_ref_view (id, ref_project, ref_sprint, ref_story, ref_task, task_stared_at, title_project, title_sprint, title_story, title_task, story_title) FROM stdin;
2449601	PUID1EBD9	SUID18F06	STUID15359	TUID1D149	2022-01-01 00:00:00	Telnet RSE	RSE Sprint 1	Gérer les sites de TELNET	Ajouter un nouveau site	\N
2449651	PUID1EBD9	SUID18F06	STUID15359	TUID1F8E3	2022-01-03 00:00:00	Telnet RSE	RSE Sprint 1	Gérer les sites de TELNET	lister les sites de TELNET disponibles	\N
2449701	PUID1EBD9	SUID18F06	STUID15359	TUID18F5D	2022-01-05 00:00:00	Telnet RSE	RSE Sprint 1	Gérer les sites de TELNET	Crud de site	\N
2449751	PUID1EBD9	SUID18F06	STUID15359	TUID17D43	2022-01-07 00:00:00	Telnet RSE	RSE Sprint 1	Gérer les sites de TELNET	Tester les crud de site	\N
2449801	PUID1EBD9	SUID18F06	STUID1F4D6	TUID1F98A	2022-01-08 00:00:00	Telnet RSE	RSE Sprint 1	La gestion d'événements	Recevoir les notifications	\N
2449851	PUID1EBD9	SUID18F06	STUID1F4D6	TUID17BCB	2022-01-11 00:00:00	Telnet RSE	RSE Sprint 1	La gestion d'événements	Apercevoir les événements en attentes.	\N
2449901	PUID1EBD9	SUID18F06	STUID1F4D6	TUID1599F	2022-01-12 00:00:00	Telnet RSE	RSE Sprint 1	La gestion d'événements	Implémenter les interfaces nécessaires pour apercevoir un événement	\N
2449951	PUID1EBD9	SUID18F06	STUID1F4D6	TUID1D593	2022-01-13 00:00:00	Telnet RSE	RSE Sprint 1	La gestion d'événements	Implémenter les méthodes et les services de validation des événements en attentes.	\N
2450001	PUID1EBD9	SUID18F06	STUID1F4D6	TUID10F60	2022-01-16 00:00:00	Telnet RSE	RSE Sprint 1	La gestion d'événements	Implémenter les méthodes et services pour participer a un événement	\N
2450051	PUID1EBD9	SUID17844	STUID107C5	TUID10D94	2022-01-20 00:00:00	Telnet RSE	RSE Sprint 2	Gérer des événements espace administrateur 	Implémenter les méthodes et les services nécessaires pour ajouter un événement	\N
2450101	PUID1EBD9	SUID17844	STUID107C5	TUID16ACC	2022-01-22 00:00:00	Telnet RSE	RSE Sprint 2	Gérer des événements espace administrateur 	Apercevoir un événément avant son ajout	\N
2450151	PUID1EBD9	SUID17844	STUID107C5	TUID1426C	2022-01-24 00:00:00	Telnet RSE	RSE Sprint 2	Gérer des événements espace administrateur 	 lister les événements disponibles et valides	\N
2450201	PUID1EBD9	SUID17844	STUID107C5	TUID14EB4	2022-01-25 00:00:00	Telnet RSE	RSE Sprint 2	Gérer des événements espace administrateur 	L'affichage l’ajout et la modification d’un événément	\N
2450251	PUID1EBD9	SUID17844	STUID107C5	TUID1E310	2022-01-30 00:00:00	Telnet RSE	RSE Sprint 2	Gérer des événements espace administrateur 	valider, réfuser et apercevoir les  événements en attente	\N
2450301	PUID1EBD9	SUID17844	STUID17802	TUID19510	2022-02-06 00:00:00	Telnet RSE	RSE Sprint 2	Gérer les évenements espace collaborateur	Implémenter les méthodes et services nécessaires pour participer a un événement	\N
2450351	PUID1EBD9	SUID17844	STUID17802	TUID1C53B	2022-02-08 00:00:00	Telnet RSE	RSE Sprint 2	Gérer les évenements espace collaborateur	Ajouter l’action de participation dans chaque événement	\N
2450401	PUID1EBD9	SUID17844	STUID17802	TUID11EF4	2022-02-09 00:00:00	Telnet RSE	RSE Sprint 2	Gérer les évenements espace collaborateur	Tester la participation	\N
2450451	PUID1EBD9	SUID17844	STUID17802	TUID1747D	2022-02-10 00:00:00	Telnet RSE	RSE Sprint 2	Gérer les évenements espace collaborateur	 lister les participants de chaque événement	\N
2450501	PUID1EBD9	SUID17844	STUID17802	TUID1BC80	2022-02-12 00:00:00	Telnet RSE	RSE Sprint 2	Gérer les évenements espace collaborateur	Ajouter les images et les noms des participants dans chaque événement	\N
2450551	PUID1EBD9	SUID17844	STUID17802	TUID1FD14	2022-02-14 00:00:00	Telnet RSE	RSE Sprint 2	Gérer les évenements espace collaborateur	Ajouter une publication	\N
2450601	PUID1EBD9	SUID17844	STUID17802	TUID15342	2022-02-15 00:00:00	Telnet RSE	RSE Sprint 2	Gérer les évenements espace collaborateur	lister les publications disponibles et valides et  apercevoir une publication avant son ajout	\N
2450651	PUID1EBD9	SUID17844	STUID17802	TUID1B042	2022-02-16 00:00:00	Telnet RSE	RSE Sprint 2	Gérer les évenements espace collaborateur	Crud de publication	\N
2450701	PUID1EBD9	SUID15D16	STUID1C8F3	TUID12BF0	2022-02-17 00:00:00	Telnet RSE	RSE Sprint 3	Gérer les publication et les commentaires espace administrateur	Recevoir des notifications	\N
2450751	PUID1EBD9	SUID15D16	STUID1C8F3	TUID13439	2022-02-18 00:00:00	Telnet RSE	RSE Sprint 3	Gérer les publication et les commentaires espace administrateur	Apercevoir les publications en attentes	\N
2450801	PUID1EBD9	SUID15D16	STUID1C8F3	TUID1C051	2022-02-19 00:00:00	Telnet RSE	RSE Sprint 3	Gérer les publication et les commentaires espace administrateur	Ajouter l’action de validation sur ie dashboard de l’administrateur RH	\N
2450851	PUID1EBD9	SUID15D16	STUID1C8F3	TUID1F3C6	2022-02-22 00:00:00	Telnet RSE	RSE Sprint 3	Gérer les publication et les commentaires espace administrateur	Tester la validation des publications en attentes	\N
2450901	PUID1EBD9	SUID15D16	STUID1C8F3	TUID1BC5C	2022-02-23 00:00:00	Telnet RSE	RSE Sprint 3	Gérer les publication et les commentaires espace administrateur	Ajouter l’action ""j’aime"" et ""commentaire""	\N
2450951	PUID1EBD9	SUID15D16	STUID1C8F3	TUID1F0EE	2022-02-24 00:00:00	Telnet RSE	RSE Sprint 3	Gérer les publication et les commentaires espace administrateur	Tester l’action ""j’aime"" et l’ajout d’un commentaire	\N
2451001	PUID1EBD9	SUID15D16	STUID15CC3	TUID11BE2	2022-02-25 00:00:00	Telnet RSE	RSE Sprint 3	Gérer les publication et les commentaires espace Com interne	Implémenter les méthodes et les services nécessaires pour ajouter une publication	\N
2451051	PUID1EBD9	SUID15D16	STUID15CC3	TUID1E167	2022-02-26 00:00:00	Telnet RSE	RSE Sprint 3	Gérer les publication et les commentaires espace Com interne	l’ajout d’une publication et  apercevoir une publication avant son ajout	\N
2451101	PUID1EBD9	SUID15D16	STUID15CC3	TUID1C095	2022-02-27 00:00:00	Telnet RSE	RSE Sprint 3	Gérer les publication et les commentaires espace Com interne	lister et afficher  les publications valides	\N
2451151	PUID1EBD9	SUID15D16	STUID15CC3	TUID1399E	2022-03-01 00:00:00	Telnet RSE	RSE Sprint 3	Gérer les publication et les commentaires espace Com interne	Recevoir des notifications	\N
2451201	PUID1EBD9	SUID15D16	STUID15CC3	TUID1A27A	2022-03-02 00:00:00	Telnet RSE	RSE Sprint 3	Gérer les publication et les commentaires espace Com interne	Ajouter l’action ""j’aime"" et  ""commentaire"" 	\N
2451251	PUID1EBD9	SUID12D17	STUID19016	TUID16051	2022-03-03 00:00:00	Telnet RSE	RSE Sprint 4	Gérer des sondages espace administrateur	Ajouter un sondage	\N
2451301	PUID1EBD9	SUID12D17	STUID19016	TUID1667A	2022-03-05 00:00:00	Telnet RSE	RSE Sprint 4	Gérer des sondages espace administrateur	Apercevoir un sondage avant son ajout	\N
2451351	PUID1EBD9	SUID12D17	STUID19016	TUID10E4F	2022-03-07 00:00:00	Telnet RSE	RSE Sprint 4	Gérer des sondages espace administrateur	Lister les sondages disponibles et valides	\N
2451401	PUID1EBD9	SUID12D17	STUID19016	TUID1E3D5	2022-03-08 00:00:00	Telnet RSE	RSE Sprint 4	Gérer des sondages espace administrateur	L’affichage,l’ajout et la modification d’un sondage	\N
2451451	PUID1EBD9	SUID12D17	STUID19016	TUID1A8E3	2022-03-10 00:00:00	Telnet RSE	RSE Sprint 4	Gérer des sondages espace administrateur	Ajouter l’action de suppression sur la page de consultation de la liste des sondages	\N
2451501	PUID1EBD9	SUID12D17	STUID19016	TUID156BD	2022-03-12 00:00:00	Telnet RSE	RSE Sprint 4	Gérer des sondages espace administrateur	Tester l’ajout et l’affichage des sondages et la suppression des sondages	\N
2451551	PUID1EBD9	SUID12D17	STUID1E069	TUID14771	2022-03-13 00:00:00	Telnet RSE	RSE Sprint 4	Gérer les notifications de sondage espace administrateur	Implémenter les méthodes et les services nécessaires pour recevoir des notifications	\N
2451601	PUID1EBD9	SUID12D17	STUID1E069	TUID14FCD	2022-03-14 00:00:00	Telnet RSE	RSE Sprint 4	Gérer les notifications de sondage espace administrateur	Ajouter l’action de validation sur ie dashboard 	\N
2451651	PUID1EBD9	SUID12D17	STUID1E069	TUID1F01A	2022-03-15 00:00:00	Telnet RSE	RSE Sprint 4	Gérer les notifications de sondage espace administrateur	validation des sondages en attentes	\N
2451701	PUID1EBD9	SUID12D17	STUID1E069	TUID11E0A	2022-03-16 00:00:00	Telnet RSE	RSE Sprint 4	Gérer les notifications de sondage espace administrateur	Tester la validation des sondages en attentes	\N
2451751	PUID1EBD9	SUID12D17	STUID1E069	TUID11F85	2022-03-17 00:00:00	Telnet RSE	RSE Sprint 4	Gérer les notifications de sondage espace administrateur	Ajouter l’action de réfusion sur ie dashboard 	\N
2451801	PUID1EBD9	SUID12D17	STUID1E069	TUID1E10B	2022-03-19 00:00:00	Telnet RSE	RSE Sprint 4	Gérer les notifications de sondage espace administrateur	Tester la réfusion des sondages en attentes	\N
2451851	PUID1EBD9	SUID12D17	STUID1E069	TUID13C8F	2022-03-20 00:00:00	Telnet RSE	RSE Sprint 4	Gérer les notifications de sondage espace administrateur	Répondre a un sondage	\N
2451901	PUID1EBD9	SUID12D17	STUID1E069	TUID1D792	2022-03-22 00:00:00	Telnet RSE	RSE Sprint 4	Gérer les notifications de sondage espace administrateur	Calculer le pourcentage de chaque réponse a chaque sondage 	\N
2451951	PUID1EBD9	SUID12D17	STUID19B86	TUID1B675	2022-03-24 00:00:00	Telnet RSE	RSE Sprint 4	Gestion des sondages espace Com intrene	lister les sondages dans la page d’accueil	\N
2452001	PUID1EBD9	SUID12D17	STUID19B86	TUID15A7C	2022-03-25 00:00:00	Telnet RSE	RSE Sprint 4	Gestion des sondages espace Com intrene	l’affichage des sondages dans la page d’accueil	\N
2452051	PUID1EBD9	SUID12D17	STUID19B86	TUID17690	2022-03-26 00:00:00	Telnet RSE	RSE Sprint 4	Gestion des sondages espace Com intrene	Tester l’action de répondre a un sondage	\N
2452101	PUID1EBD9	SUID12D17	STUID19B86	TUID1B9C6	2022-03-27 00:00:00	Telnet RSE	RSE Sprint 4	Gestion des sondages espace Com intrene	l’affichage des sondages dans la page d’accueil	\N
2452151	PUID1EBD9	SUID12D17	STUID19B86	TUID1263E	2022-03-29 00:00:00	Telnet RSE	RSE Sprint 4	Gestion des sondages espace Com intrene	Implémenter les méthodes de répondre a un sondage et tester l’action de répondre a un sondage	\N
2452201	PUID1EBD9	SUID154EC	STUID141D2	TUID15715	2022-03-31 00:00:00	Telnet RSE	RSE Sprint 5	Consulter mon profile espace administrateur	Récupérer les données de l’employé	\N
2452251	PUID1EBD9	SUID154EC	STUID141D2	TUID12187	2022-04-01 00:00:00	Telnet RSE	RSE Sprint 5	Consulter mon profile espace administrateur	Récupérer les publications de l’employé	\N
2452301	PUID1EBD9	SUID154EC	STUID141D2	TUID1433A	2022-04-02 00:00:00	Telnet RSE	RSE Sprint 5	Consulter mon profile espace administrateur	Réagir avec les publications	\N
2452351	PUID1EBD9	SUID154EC	STUID14272	TUID19CD8	2022-04-03 00:00:00	Telnet RSE	RSE Sprint 5	Gérer mon profile espace com interne	Gérer les données personnelles	\N
2452401	PUID1EBD9	SUID154EC	STUID14272	TUID127C5	2022-04-04 00:00:00	Telnet RSE	RSE Sprint 5	Gérer mon profile espace com interne	Implémenter les méthodes et les services nécessaires pour ajouter des données personnelles	\N
2452451	PUID1EBD9	SUID154EC	STUID14272	TUID1003B	2022-04-05 00:00:00	Telnet RSE	RSE Sprint 5	Gérer mon profile espace com interne	Implémenter les méthodes et les services nécessaires pour mettre a jour des données personnelles	\N
2452501	PUID1EBD9	SUID154EC	STUID14272	TUID1F20C	2022-04-06 00:00:00	Telnet RSE	RSE Sprint 5	Gérer mon profile espace com interne	Tester la modification des données personnelles.	\N
2452551	PUID1EBD9	SUID154EC	STUID14272	TUID11A47	2022-04-07 00:00:00	Telnet RSE	RSE Sprint 5	Gérer mon profile espace com interne	Ajouter des compétences	\N
2452601	PUID1EBD9	SUID154EC	STUID14272	TUID14490	2022-04-08 00:00:00	Telnet RSE	RSE Sprint 5	Gérer mon profile espace com interne	Ajouter des centres d’intérét	\N
2452651	PUID1EBD9	SUID154EC	STUID14272	TUID17B33	2022-04-09 00:00:00	Telnet RSE	RSE Sprint 5	Gérer mon profile espace com interne	Tester l’ajout des compétences et l’ajout des centres d’intérét	\N
2452701	PUID1EBD9	SUID154EC	STUID14272	TUID1453B	2022-04-10 00:00:00	Telnet RSE	RSE Sprint 5	Gérer mon profile espace com interne	Ajouter des projets réalisés	\N
2452751	PUID1EBD9	SUID154EC	STUID14272	TUID17C79	2022-04-12 00:00:00	Telnet RSE	RSE Sprint 5	Gérer mon profile espace com interne	Ajouter l’action sur les images et les noms des employés	\N
2452801	PUID1EBD9	SUID154EC	STUID14272	TUID16466	2022-04-14 00:00:00	Telnet RSE	RSE Sprint 5	Gérer mon profile espace com interne	Récupérer ie profile de chaque employé	\N
2452851	PUID1EBD9	SUID154EC	STUID14272	TUID16CFF	2022-04-15 00:00:00	Telnet RSE	RSE Sprint 5	Gérer mon profile espace com interne	Ajouter l’action sur les images et les noms de com interne	\N
2452901	PUID1EBD9	SUID154EC	STUID14272	TUID11E9B	2022-04-17 00:00:00	Telnet RSE	RSE Sprint 5	Gérer mon profile espace com interne	Consulter les profiles des autres employés espace  Com interne	\N
2452951	PUID1EBD9	SUID154EC	STUID1BB3F	TUID1D1B8	2022-04-18 00:00:00	Telnet RSE	RSE Sprint 5	Gérer mes données personnelles dans mon profile espace collaborateur	Ajouter un bouton dans ie profile qui redirige vers une page de modification des données personnelles	\N
2453001	PUID1EBD9	SUID154EC	STUID1BB3F	TUID1C6F4	2022-04-19 00:00:00	Telnet RSE	RSE Sprint 5	Gérer mes données personnelles dans mon profile espace collaborateur	Implémenter les méthodes et les services nécessaires pour ajouter des données personnelles	\N
2453051	PUID1EBD9	SUID154EC	STUID1BB3F	TUID14277	2022-04-21 00:00:00	Telnet RSE	RSE Sprint 5	Gérer mes données personnelles dans mon profile espace collaborateur	Mettre a jour des données personnelles	\N
2453101	PUID1EBD9	SUID154EC	STUID1BB3F	TUID1467C	2022-04-22 00:00:00	Telnet RSE	RSE Sprint 5	Gérer mes données personnelles dans mon profile espace collaborateur	Ajouter des compétences	\N
2453151	PUID1EBD9	SUID154EC	STUID1BB3F	TUID1E43B	2022-04-23 00:00:00	Telnet RSE	RSE Sprint 5	Gérer mes données personnelles dans mon profile espace collaborateur	Ajouter des centres d’intérét	\N
2453201	PUID1EBD9	SUID154EC	STUID1BB3F	TUID19855	2022-04-24 00:00:00	Telnet RSE	RSE Sprint 5	Gérer mes données personnelles dans mon profile espace collaborateur	Regrouper les empoyés qui ont les mémes compétences et centres d’intérét	\N
2453251	PUID1EBD9	SUID154EC	STUID1BB3F	TUID1C4DF	2022-04-25 00:00:00	Telnet RSE	RSE Sprint 5	Gérer mes données personnelles dans mon profile espace collaborateur	Consulter les recommandations disponibles dans la page d’accueil	\N
2453301	PUID1EBD9	SUID154EC	STUID1BB3F	TUID1B913	2022-04-26 00:00:00	Telnet RSE	RSE Sprint 5	Gérer mes données personnelles dans mon profile espace collaborateur	regrouper les empoyés qui ont les mémes compétences et centres d’intérét pour les com interne	\N
2453351	PUID1441B	SUID118F5	STUID1A273	TUID181D3	2022-03-06 00:00:00	Gestion des fournisseurs de l’ERP Byblos	Byblοs Sprint 1	Authentification	Ajοuter la cοuche Spring Security	\N
2453401	PUID1441B	SUID118F5	STUID1A273	TUID17BF7	2022-03-07 00:00:00	Gestion des fournisseurs de l’ERP Byblos	Byblοs Sprint 1	Authentification	Intégrer le module authentification	\N
2453451	PUID1441B	SUID118F5	STUID1C117	TUID1CC1C	2022-03-11 00:00:00	Gestion des fournisseurs de l’ERP Byblos	Byblοs Sprint 1	Mise en place de l’architecture	définir l'architecture logique de la partie back-end	\N
2453501	PUID1441B	SUID118F5	STUID1C117	TUID16FDC	2022-03-12 00:00:00	Gestion des fournisseurs de l’ERP Byblos	Byblοs Sprint 1	Mise en place de l’architecture	Implémenter CRUD	\N
2453551	PUID1441B	SUID118F5	STUID1C117	TUID1AE80	2022-03-20 00:00:00	Gestion des fournisseurs de l’ERP Byblos	Byblοs Sprint 1	Mise en place de l’architecture	La création des interfaces UI de crud	\N
2453601	PUID1441B	SUID118F5	STUID1C117	TUID141E6	2022-03-22 00:00:00	Gestion des fournisseurs de l’ERP Byblos	Byblοs Sprint 1	Mise en place de l’architecture	Les méthodes de cοntrôle de saisie	\N
2453651	PUID1441B	SUID16D4B	STUID1DE08	TUID1C3A6	2022-03-25 00:00:00	Gestion des fournisseurs de l’ERP Byblos	Byblοs Sprint 2	Conception et réalisation de ces fonctionnalité	Créer les méthodes dans la partie front-end	\N
2453701	PUID1441B	SUID16D4B	STUID1DE08	TUID17617	2022-04-01 00:00:00	Gestion des fournisseurs de l’ERP Byblos	Byblοs Sprint 2	Conception et réalisation de ces fonctionnalité	Intégrer les modules	\N
2453751	PUID1441B	SUID16D4B	STUID1ABEF	TUID130AB	2022-04-03 00:00:00	Gestion des fournisseurs de l’ERP Byblos	Byblοs Sprint 2	Gestion des fournisseurs et gestion des sous-traitants	Créer les repositories, DTOs, les services et les contrôleurs.	\N
2453801	PUID1441B	SUID16D4B	STUID1ABEF	TUID14A02	2022-04-04 00:00:00	Gestion des fournisseurs de l’ERP Byblos	Byblοs Sprint 2	Gestion des fournisseurs et gestion des sous-traitants	Crud des sous-traitants	\N
2453851	PUID1441B	SUID16D4B	STUID1ABEF	TUID1CB60	2022-04-10 00:00:00	Gestion des fournisseurs de l’ERP Byblos	Byblοs Sprint 2	Gestion des fournisseurs et gestion des sous-traitants	Créer les interfaces pour la gestion des sous-traitants	\N
2453901	PUID1441B	SUID16D4B	STUID1ABEF	TUID14CA5	2022-04-12 00:00:00	Gestion des fournisseurs de l’ERP Byblos	Byblοs Sprint 2	Gestion des fournisseurs et gestion des sous-traitants	Implémenter les méthodes et le contrôle de saisie .	\N
2453951	PUID1441B	SUID16D4B	STUID1ABEF	TUID1EA4C	2022-04-14 00:00:00	Gestion des fournisseurs de l’ERP Byblos	Byblοs Sprint 2	Gestion des fournisseurs et gestion des sous-traitants	Intégrer les modules	\N
2454001	PUID1441B	SUID168B1	STUID1C9F0	TUID1EDC4	2022-04-16 00:00:00	Gestion des fournisseurs de l’ERP Byblos	Byblοss Sprint 3	La gestion des contrats	l’architecture logique de la partie back-end des contrats	\N
2454051	PUID1441B	SUID168B1	STUID1C9F0	TUID1D69E	2022-04-17 00:00:00	Gestion des fournisseurs de l’ERP Byblos	Byblοss Sprint 3	La gestion des contrats	CRUD methode pour les contrats	\N
2454101	PUID1441B	SUID168B1	STUID1C9F0	TUID1A887	2022-04-24 00:00:00	Gestion des fournisseurs de l’ERP Byblos	Byblοss Sprint 3	La gestion des contrats	Créer les interfaces 	\N
2454151	PUID1441B	SUID168B1	STUID1C9F0	TUID1D6D0	2022-04-26 00:00:00	Gestion des fournisseurs de l’ERP Byblos	Byblοss Sprint 3	La gestion des contrats	Implémenter les méthodes de la partie front-end.	\N
2454201	PUID1441B	SUID168B1	STUID1C9F0	TUID118E9	2022-04-29 00:00:00	Gestion des fournisseurs de l’ERP Byblos	Byblοss Sprint 3	La gestion des contrats	Implémenter les méthodes de contrôle de saisie pour les formulaires.	\N
2454251	PUID1441B	SUID168B1	STUID133F6	TUID1AA8A	2022-04-30 00:00:00	Gestion des fournisseurs de l’ERP Byblos	Byblοss Sprint 3	La gestion des factures	Créer le repository, les DTOs, les services 	\N
2454301	PUID1441B	SUID168B1	STUID133F6	TUID1EC8F	2022-05-01 00:00:00	Gestion des fournisseurs de l’ERP Byblos	Byblοss Sprint 3	La gestion des factures	Recueillir les infοrmatiοns	\N
2454351	PUID1441B	SUID18F04	STUID1F57D	TUID1381A	2022-05-06 00:00:00	Gestion des fournisseurs de l’ERP Byblos	Byblοss Sprint 4	la suite de la gestion des factures	Implémenter les méthοdes de consultation et de recherche	\N
2454401	PUID1441B	SUID18F04	STUID1F57D	TUID1859E	2022-05-10 00:00:00	Gestion des fournisseurs de l’ERP Byblos	Byblοss Sprint 4	la suite de la gestion des factures	Créer les interfaces nécessaires.	\N
2454451	PUID1441B	SUID18F04	STUID1F57D	TUID1F269	2022-05-12 00:00:00	Gestion des fournisseurs de l’ERP Byblos	Byblοss Sprint 4	la suite de la gestion des factures	Implémenter les méthοdes de la partie frοnt-end.	\N
2454501	PUID1441B	SUID18F04	STUID1DE58	TUID1A7AF	2022-05-16 00:00:00	Gestion des fournisseurs de l’ERP Byblos	Byblοss Sprint 4	La gestion des documents	Créer le Dοcument DTΟ et le service et le service implémentatiοn.	\N
2454551	PUID1441B	SUID18F04	STUID1DE58	TUID187A0	2022-05-17 00:00:00	Gestion des fournisseurs de l’ERP Byblos	Byblοss Sprint 4	La gestion des documents	Créer la méthοde uplοad du dοcument 	\N
2454601	PUID1441B	SUID18F04	STUID1DE58	TUID17F09	2022-05-19 00:00:00	Gestion des fournisseurs de l’ERP Byblos	Byblοss Sprint 4	La gestion des documents	Miniature d’un document enregistré	\N
2454651	PUID1441B	SUID18F04	STUID1DE58	TUID1F65B	2022-05-22 00:00:00	Gestion des fournisseurs de l’ERP Byblos	Byblοss Sprint 4	La gestion des documents	Implémenter la méthode de téléchargement	\N
2454701	PUID1441B	SUID18F04	STUID1DE58	TUID19F05	2022-05-24 00:00:00	Gestion des fournisseurs de l’ERP Byblos	Byblοss Sprint 4	La gestion des documents	Manipuler les documents	\N
2454751	PUID1441B	SUID18F04	STUID1DE58	TUID1FD33	2022-05-26 00:00:00	Gestion des fournisseurs de l’ERP Byblos	Byblοss Sprint 4	La gestion des documents	Implementation des méthodes cotés front-end	\N
2454801	PUID1441B	SUID141EB	STUID1837D	TUID19C54	2022-05-30 00:00:00	Gestion des fournisseurs de l’ERP Byblos	Byblοs Sprint 5	Export des données	Créer la méthοde d’expοrt dans la partie Back-end	\N
2454851	PUID1441B	SUID141EB	STUID1837D	TUID1558A	2022-06-04 00:00:00	Gestion des fournisseurs de l’ERP Byblos	Byblοs Sprint 5	Export des données	Ajοuter l’οptiοn expοrter dans UI	\N
2454901	PUID1441B	SUID141EB	STUID1837D	TUID16B23	2022-06-05 00:00:00	Gestion des fournisseurs de l’ERP Byblos	Byblοs Sprint 5	Export des données	Créer la méthode de l’export dans la partie front-end	\N
2454951	PUID1441B	SUID141EB	STUID19E2E	TUID120B2	2022-06-07 00:00:00	Gestion des fournisseurs de l’ERP Byblos	Byblοs Sprint 5	Personnalisation du dashboard et statistiques	Ajouter au menu de l’application tous les liens utiles	\N
2455001	PUID1441B	SUID141EB	STUID19E2E	TUID12DCF	2022-06-09 00:00:00	Gestion des fournisseurs de l’ERP Byblos	Byblοs Sprint 5	Personnalisation du dashboard et statistiques	Ajοuter le widget fοurnisseur à la base de dοnnées.	\N
2455051	PUID1441B	SUID141EB	STUID19E2E	TUID1B421	2022-06-10 00:00:00	Gestion des fournisseurs de l’ERP Byblos	Byblοs Sprint 5	Personnalisation du dashboard et statistiques	Créer des statistiques	\N
2455101	PUID1441B	SUID141EB	STUID19E2E	TUID1272B	2022-06-13 00:00:00	Gestion des fournisseurs de l’ERP Byblos	Byblοs Sprint 5	Personnalisation du dashboard et statistiques	Manage widget fοurnisseur	\N
2455151	PUID1441B	SUID141EB	STUID1951D	TUID1CCA2	2022-06-17 00:00:00	Gestion des fournisseurs de l’ERP Byblos	Byblοs Sprint 5	Traduction	Créer les scripts de la traduction	\N
2455201	PUID1441B	SUID141EB	STUID1951D	TUID11326	2022-06-20 00:00:00	Gestion des fournisseurs de l’ERP Byblos	Byblοs Sprint 5	Traduction	Récupérer la liste des champs traduit	\N
2455251	PUID1441B	SUID141EB	STUID1951D	TUID1E316	2022-06-21 00:00:00	Gestion des fournisseurs de l’ERP Byblos	Byblοs Sprint 5	Traduction	Implémenter la traductiοn dans la partie frοnt-end 	\N
2455301	PUID1441B	SUID141EB	STUID1951D	TUID1CC09	2022-06-26 00:00:00	Gestion des fournisseurs de l’ERP Byblos	Byblοs Sprint 5	Traduction	Implémenter la traductiοn dans tοutes les interfaces de l’applicatiοn.	\N
\.


--
-- Data for Name: days_sprints; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.days_sprints (sprint_id, days_array) FROM stdin;
1	11-03-2022
1	12-03-2022
1	13-03-2022
1	14-03-2022
1	15-03-2022
1	16-03-2022
1	17-03-2022
1	18-03-2022
1	19-03-2022
1	20-03-2022
1	21-03-2022
1	22-03-2022
1	23-03-2022
1	24-03-2022
1	{"(06-03-2022,07-03-2022,08-03-2022,09-03-2022,10-03-2022,11-03-2022,12-03-2022,13-03-2022,14-03-2022,15-03-2022,16-03-2022,17-03-2022,18-03-2022,19-03-2022,20-03-2022,21-03-2022,22-03-2022,23-03-2022,24-03-2022)"}
152	20-01-2022
152	21-01-2022
152	22-01-2022
152	23-01-2022
152	24-01-2022
152	25-01-2022
152	26-01-2022
152	27-01-2022
152	28-01-2022
152	29-01-2022
152	30-01-2022
152	31-01-2022
152	01-02-2022
152	02-02-2022
152	03-02-2022
152	04-02-2022
152	05-02-2022
152	06-02-2022
152	07-02-2022
152	08-02-2022
152	09-02-2022
152	10-02-2022
152	11-02-2022
152	12-02-2022
152	13-02-2022
152	14-02-2022
152	15-02-2022
152	16-02-2022
152	{"(20-01-2022,21-01-2022,22-01-2022,23-01-2022,24-01-2022,25-01-2022,26-01-2022,27-01-2022,28-01-2022,29-01-2022,30-01-2022,31-01-2022,01-02-2022,02-02-2022,03-02-2022,04-02-2022,05-02-2022,06-02-2022,07-02-2022,08-02-2022,09-02-2022,10-02-2022,11-02-2022,12-02-2022,13-02-2022,14-02-2022,15-02-2022,16-02-2022)"}
302	17-02-2022
302	18-02-2022
302	19-02-2022
302	20-02-2022
302	21-02-2022
302	22-02-2022
302	23-02-2022
302	24-02-2022
302	25-02-2022
302	26-02-2022
302	27-02-2022
302	28-02-2022
302	01-03-2022
302	02-03-2022
302	{"(17-02-2022,18-02-2022,19-02-2022,20-02-2022,21-02-2022,22-02-2022,23-02-2022,24-02-2022,25-02-2022,26-02-2022,27-02-2022,28-02-2022,01-03-2022,02-03-2022)"}
202	03-03-2022
202	04-03-2022
202	05-03-2022
202	06-03-2022
202	07-03-2022
202	08-03-2022
202	09-03-2022
202	10-03-2022
202	11-03-2022
202	12-03-2022
202	13-03-2022
202	14-03-2022
202	15-03-2022
202	16-03-2022
202	17-03-2022
202	18-03-2022
202	19-03-2022
202	20-03-2022
202	21-03-2022
202	22-03-2022
202	23-03-2022
202	24-03-2022
202	25-03-2022
202	26-03-2022
202	27-03-2022
202	28-03-2022
202	29-03-2022
202	30-03-2022
202	{"(03-03-2022,04-03-2022,05-03-2022,06-03-2022,07-03-2022,08-03-2022,09-03-2022,10-03-2022,11-03-2022,12-03-2022,13-03-2022,14-03-2022,15-03-2022,16-03-2022,17-03-2022,18-03-2022,19-03-2022,20-03-2022,21-03-2022,22-03-2022,23-03-2022,24-03-2022,25-03-2022,26-03-2022,27-03-2022,28-03-2022,29-03-2022,30-03-2022)"}
304	06-05-2022
304	07-05-2022
304	08-05-2022
304	09-05-2022
304	10-05-2022
304	11-05-2022
304	12-05-2022
304	13-05-2022
304	14-05-2022
304	15-05-2022
304	16-05-2022
304	17-05-2022
304	18-05-2022
304	19-05-2022
304	20-05-2022
304	21-05-2022
304	22-05-2022
304	23-05-2022
304	24-05-2022
304	25-05-2022
304	26-05-2022
304	27-05-2022
304	28-05-2022
304	{"(06-05-2022,07-05-2022,08-05-2022,09-05-2022,10-05-2022,11-05-2022,12-05-2022,13-05-2022,14-05-2022,15-05-2022,16-05-2022,17-05-2022,18-05-2022,19-05-2022,20-05-2022,21-05-2022,22-05-2022,23-05-2022,24-05-2022,25-05-2022,26-05-2022,27-05-2022,28-05-2022)"}
3	29-05-2022
3	30-05-2022
3	31-05-2022
3	01-06-2022
3	02-06-2022
3	03-06-2022
3	04-06-2022
3	05-06-2022
3	06-06-2022
3	07-06-2022
3	08-06-2022
3	09-06-2022
3	10-06-2022
3	11-06-2022
3	12-06-2022
3	13-06-2022
3	14-06-2022
3	15-06-2022
3	16-06-2022
3	17-06-2022
3	18-06-2022
3	19-06-2022
3	20-06-2022
3	21-06-2022
3	22-06-2022
3	23-06-2022
3	24-06-2022
3	25-06-2022
3	26-06-2022
3	27-06-2022
3	{"(29-05-2022,30-05-2022,31-05-2022,01-06-2022,02-06-2022,03-06-2022,04-06-2022,05-06-2022,06-06-2022,07-06-2022,08-06-2022,09-06-2022,10-06-2022,11-06-2022,12-06-2022,13-06-2022,14-06-2022,15-06-2022,16-06-2022,17-06-2022,18-06-2022,19-06-2022,20-06-2022,21-06-2022,22-06-2022,23-06-2022,24-06-2022,25-06-2022,26-06-2022,27-06-2022)"}
252	31-03-2022
252	01-04-2022
252	02-04-2022
252	03-04-2022
252	04-04-2022
252	05-04-2022
252	06-04-2022
252	07-04-2022
252	08-04-2022
252	09-04-2022
252	10-04-2022
252	11-04-2022
252	12-04-2022
252	13-04-2022
252	14-04-2022
252	15-04-2022
252	16-04-2022
252	17-04-2022
252	18-04-2022
252	19-04-2022
252	20-04-2022
252	21-04-2022
252	22-04-2022
252	23-04-2022
252	24-04-2022
252	25-04-2022
252	26-04-2022
252	27-04-2022
252	{"(31-03-2022,01-04-2022,02-04-2022,03-04-2022,04-04-2022,05-04-2022,06-04-2022,07-04-2022,08-04-2022,09-04-2022,10-04-2022,11-04-2022,12-04-2022,13-04-2022,14-04-2022,15-04-2022,16-04-2022,17-04-2022,18-04-2022,19-04-2022,20-04-2022,21-04-2022,22-04-2022,23-04-2022,24-04-2022,25-04-2022,26-04-2022,27-04-2022)"}
107	01-01-2022
107	02-01-2022
107	03-01-2022
107	04-01-2022
107	05-01-2022
107	06-01-2022
107	07-01-2022
107	08-01-2022
107	09-01-2022
107	10-01-2022
107	11-01-2022
107	12-01-2022
107	13-01-2022
107	14-01-2022
107	15-01-2022
107	16-01-2022
107	17-01-2022
107	18-01-2022
107	19-01-2022
107	{"(01-01-2022,02-01-2022,03-01-2022,04-01-2022,05-01-2022,06-01-2022,07-01-2022,08-01-2022,09-01-2022,10-01-2022,11-01-2022,12-01-2022,13-01-2022,14-01-2022,15-01-2022,16-01-2022,17-01-2022,18-01-2022,19-01-2022)"}
303	25-03-2022
303	26-03-2022
303	27-03-2022
303	28-03-2022
303	29-03-2022
303	30-03-2022
303	31-03-2022
303	01-04-2022
303	02-04-2022
303	03-04-2022
303	04-04-2022
303	05-04-2022
303	06-04-2022
303	07-04-2022
303	08-04-2022
303	09-04-2022
303	10-04-2022
303	11-04-2022
303	12-04-2022
303	13-04-2022
303	14-04-2022
303	15-04-2022
303	{"(25-03-2022,26-03-2022,27-03-2022,28-03-2022,29-03-2022,30-03-2022,31-03-2022,01-04-2022,02-04-2022,03-04-2022,04-04-2022,05-04-2022,06-04-2022,07-04-2022,08-04-2022,09-04-2022,10-04-2022,11-04-2022,12-04-2022,13-04-2022,14-04-2022,15-04-2022)"}
2	16-04-2022
2	17-04-2022
2	18-04-2022
2	19-04-2022
2	20-04-2022
2	21-04-2022
2	22-04-2022
2	23-04-2022
2	24-04-2022
2	25-04-2022
2	26-04-2022
2	27-04-2022
2	28-04-2022
2	29-04-2022
2	30-04-2022
2	01-05-2022
2	02-05-2022
2	03-05-2022
2	04-05-2022
2	05-05-2022
2	{"(16-04-2022,17-04-2022,18-04-2022,19-04-2022,20-04-2022,21-04-2022,22-04-2022,23-04-2022,24-04-2022,25-04-2022,26-04-2022,27-04-2022,28-04-2022,29-04-2022,30-04-2022,01-05-2022,02-05-2022,03-05-2022,04-05-2022,05-05-2022)"}
1	06-03-2022
1	07-03-2022
1	08-03-2022
1	09-03-2022
1	10-03-2022
\.


--
-- Data for Name: ideall_sprints; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.ideall_sprints (sprint_id, il_array) FROM stdin;
252	26.962963
252	25.925926
252	24.88889
252	23.851852
252	22.814816
252	21.777779
252	20.740742
252	19.703705
252	18.666668
252	17.629631
252	16.592594
252	15.555557
252	14.51852
252	13.481483
252	12.444447
252	11.40741
252	10.370373
252	9.333336
252	8.296299
252	7.259262
252	6.222225
252	5.1851883
252	4.1481514
252	3.1111145
252	2.0740776
252	1.0370406
252	{"(28.0,26.962963,25.925926,24.88889,23.851852,22.814816,21.777779,20.740742,19.703705,18.666668,17.629631,16.592594,15.555557,14.51852,13.481483,12.444447,11.40741,10.370373,9.333336,8.296299,7.259262,6.222225,5.1851883,4.1481514,3.1111145,2.0740776,1.0370406)"}
107	18.0
107	17.0
107	16.0
107	15.0
107	14.0
107	13.0
107	12.0
107	11.0
107	10.0
107	9.0
107	8.0
107	7.0
107	6.0
107	5.0
107	4.0
107	3.0
107	2.0
107	1.0
107	{"(18.0,17.0,16.0,15.0,14.0,13.0,12.0,11.0,10.0,9.0,8.0,7.0,6.0,5.0,4.0,3.0,2.0,1.0)"}
303	21.0
303	20.0
303	19.0
303	18.0
303	17.0
303	16.0
303	15.0
303	14.0
303	13.0
303	12.0
303	11.0
303	10.0
303	9.0
303	8.0
303	7.0
303	6.0
303	5.0
303	4.0
303	3.0
303	2.0
303	1.0
303	{"(21.0,20.0,19.0,18.0,17.0,16.0,15.0,14.0,13.0,12.0,11.0,10.0,9.0,8.0,7.0,6.0,5.0,4.0,3.0,2.0,1.0)"}
2	21.0
2	19.894737
2	18.789474
2	17.684212
2	16.578949
2	15.473686
2	14.368423
2	13.263161
2	12.157898
2	11.052635
2	9.947372
2	8.84211
2	7.7368464
2	6.631583
2	5.52632
2	4.4210567
2	3.3157935
2	2.2105303
2	1.1052672
2	{"(21.0,19.894737,18.789474,17.684212,16.578949,15.473686,14.368423,13.263161,12.157898,11.052635,9.947372,8.84211,7.7368464,6.631583,5.52632,4.4210567,3.3157935,2.2105303,1.1052672)"}
1	19.0
1	17.944445
152	28.0
152	26.962963
152	25.925926
152	24.88889
152	23.851852
152	22.814816
152	21.777779
152	20.740742
152	19.703705
152	18.666668
152	17.629631
152	16.592594
152	15.555557
152	14.51852
152	13.481483
152	12.444447
152	11.40741
152	10.370373
152	9.333336
152	8.296299
152	7.259262
152	6.222225
152	5.1851883
152	4.1481514
152	3.1111145
152	2.0740776
152	1.0370406
152	{"(28.0,26.962963,25.925926,24.88889,23.851852,22.814816,21.777779,20.740742,19.703705,18.666668,17.629631,16.592594,15.555557,14.51852,13.481483,12.444447,11.40741,10.370373,9.333336,8.296299,7.259262,6.222225,5.1851883,4.1481514,3.1111145,2.0740776,1.0370406)"}
302	14.0
302	12.923077
302	11.846153
302	10.76923
302	9.6923065
302	8.615383
302	7.53846
302	6.4615364
302	5.384613
302	4.3076897
302	3.2307665
302	2.1538434
302	1.0769203
302	{"(14.0,12.923077,11.846153,10.76923,9.6923065,8.615383,7.53846,6.4615364,5.384613,4.3076897,3.2307665,2.1538434,1.0769203)"}
202	28.0
202	26.962963
202	25.925926
202	24.88889
202	23.851852
202	22.814816
202	21.777779
202	20.740742
202	19.703705
202	18.666668
202	17.629631
202	16.592594
202	15.555557
202	14.51852
202	13.481483
202	12.444447
202	11.40741
202	10.370373
202	9.333336
202	8.296299
202	7.259262
202	6.222225
202	5.1851883
202	4.1481514
202	3.1111145
202	2.0740776
202	1.0370406
202	{"(28.0,26.962963,25.925926,24.88889,23.851852,22.814816,21.777779,20.740742,19.703705,18.666668,17.629631,16.592594,15.555557,14.51852,13.481483,12.444447,11.40741,10.370373,9.333336,8.296299,7.259262,6.222225,5.1851883,4.1481514,3.1111145,2.0740776,1.0370406)"}
304	23.0
304	21.954546
304	20.909092
304	19.863638
304	18.818184
304	17.77273
304	16.727276
304	15.681822
304	14.636368
304	13.590914
304	12.54546
304	11.500006
304	10.454552
304	9.409098
304	8.363644
304	7.318189
304	6.2727346
304	5.22728
304	4.1818256
304	3.1363711
304	2.0909166
304	1.0454621
304	{"(23.0,21.954546,20.909092,19.863638,18.818184,17.77273,16.727276,15.681822,14.636368,13.590914,12.54546,11.500006,10.454552,9.409098,8.363644,7.318189,6.2727346,5.22728,4.1818256,3.1363711,2.0909166,1.0454621)"}
3	27.0
3	26.068966
3	25.137932
3	24.206898
3	23.275864
3	22.34483
3	21.413795
3	20.482761
3	19.551727
3	18.620693
3	17.68966
3	16.758625
3	15.827591
3	14.896557
3	13.965523
3	13.034489
3	12.103455
3	11.1724205
3	10.241386
3	9.310352
3	8.379318
3	7.4482837
3	6.517249
3	5.5862145
3	4.65518
3	3.7241454
3	2.7931108
3	1.8620763
3	0.9310418
3	{"(27.0,26.068966,25.137932,24.206898,23.275864,22.34483,21.413795,20.482761,19.551727,18.620693,17.68966,16.758625,15.827591,14.896557,13.965523,13.034489,12.103455,11.1724205,10.241386,9.310352,8.379318,7.4482837,6.517249,5.5862145,4.65518,3.7241454,2.7931108,1.8620763,0.9310418)"}
252	28.0
1	16.88889
1	15.833334
1	14.777779
1	13.722223
1	12.666668
1	11.611113
1	10.555557
1	9.500002
1	8.444447
1	7.388891
1	6.333336
1	5.2777805
1	4.222225
1	3.1666696
1	2.111114
1	1.0555584
1	{"(19.0,17.944445,16.88889,15.833334,14.777779,13.722223,12.666668,11.611113,10.555557,9.500002,8.444447,7.388891,6.333336,5.2777805,4.222225,3.1666696,2.111114,1.0555584)"}
\.


--
-- Data for Name: jcoverage; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.jcoverage (id, covered, created_at, missed, percentage, project_ref, projectname, totalpercentage, type) FROM stdin;
152	420	2023-01-12 15:00:27.69	6412	6.15	PUID1EBD9	Telnet RSE v3	14.283333	INSTRUCTION
153	54	2023-01-12 15:00:27.693	246	18	PUID1EBD9	Telnet RSE v3	14.283333	BRANCH
154	78	2023-01-12 15:00:27.694	1417	5.22	PUID1EBD9	Telnet RSE v3	14.283333	LINE
155	76	2023-01-12 15:00:27.694	623	10.87	PUID1EBD9	Telnet RSE v3	14.283333	COMPLEXITY
156	53	2023-01-12 15:00:27.695	468	10.17	PUID1EBD9	Telnet RSE v3	14.283333	METHOD
157	18	2023-01-12 15:00:27.695	33	35.29	PUID1EBD9	Telnet RSE v3	14.283333	CLASS
158	6412	2023-01-12 15:00:34.839	420	93.85	PUID1EBD9	Telnet RSE v4	83.62333	INSTRUCTION
159	246	2023-01-12 15:00:34.841	4	98.4	PUID1EBD9	Telnet RSE v4	83.62333	BRANCH
160	1417	2023-01-12 15:00:34.841	89	94.09	PUID1EBD9	Telnet RSE v4	83.62333	LINE
161	586	2023-01-12 15:00:34.841	53	91.71	PUID1EBD9	Telnet RSE v4	83.62333	COMPLEXITY
162	461	2023-01-12 15:00:34.842	53	89.69	PUID1EBD9	Telnet RSE v4	83.62333	METHOD
163	17	2023-01-12 15:00:34.842	33	34	PUID1EBD9	Telnet RSE v4	83.62333	CLASS
164	1550	2023-01-12 15:00:57.303	5332	22.52	PUID1441B	ERP Byblos v2	21.088333	INSTRUCTION
165	35	2023-01-12 15:00:57.304	280	11.11	PUID1441B	ERP Byblos v2	21.088333	BRANCH
166	600	2023-01-12 15:00:57.305	1016	37.13	PUID1441B	ERP Byblos v2	21.088333	LINE
167	120	2023-01-12 15:00:57.305	725	14.2	PUID1441B	ERP Byblos v2	21.088333	COMPLEXITY
168	60	2023-01-12 15:00:57.306	570	9.52	PUID1441B	ERP Byblos v2	21.088333	METHOD
169	25	2023-01-12 15:00:57.306	53	32.05	PUID1441B	ERP Byblos v2	21.088333	CLASS
170	5332	2023-01-12 15:01:05.326	1550	77.48	PUID1441B	ERP Byblos v5	73.13	INSTRUCTION
171	280	2023-01-12 15:01:05.327	35	88.89	PUID1441B	ERP Byblos v5	73.13	BRANCH
172	1016	2023-01-12 15:01:05.327	600	62.87	PUID1441B	ERP Byblos v5	73.13	LINE
173	686	2023-01-12 15:01:05.327	73	90.38	PUID1441B	ERP Byblos v5	73.13	COMPLEXITY
174	561	2023-01-12 15:01:05.328	83	87.11	PUID1441B	ERP Byblos v5	73.13	METHOD
175	25	2023-01-12 15:01:05.328	53	32.05	PUID1441B	ERP Byblos v5	73.13	CLASS
\.


--
-- Data for Name: member_of_project; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.member_of_project (project_id, email) FROM stdin;
52	yassine@scrumm.tn
52	iheb@developer.tn
52	chamsEddine@productowner.tn
52	tasnime@developer.tn
\.


--
-- Data for Name: member_of_task; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.member_of_task (task_attached_id, mail) FROM stdin;
202	syrine@scrumm.tn
202	mohamed@developer.tn
202	aymen@productowner.tn
202	amine@developer.tn
466	yassine@scrumm.tn
466	iheb@developer.tn
466	chamsEddine@productowner.tn
466	tasnime@developer.tn
327	iheb@developer.tn
327	mohamed@developer.tn
327	amine@developer.tn
327	tasnime@developer.tn
\.


--
-- Data for Name: projects; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.projects (id, date_debut, date_fin, description, p_reference, pupdated_date, title, totalsp_commitment, totalsp_completed, totalstorypointsinitiallycounts) FROM stdin;
1	2022-03-06	2022-06-27	Refonte de module Gestion des Fournisseurs de Byblos consiste à assurer une refonte architecturale et fonctionnelle de module Gestion des Fournisseurs\nen migrant la solution existante du framework JSF vers une nouvelle solution utilisant Spring Boot et\nAngular et en passant de l’architecture monolithique vers une architecture à base de microservices.\n\nLe résultat souhaité est de réaliser une applicatiοn fiable, maintenable et évοlutive, facile à utiliser et qui\nallège les cοmplexités de l’architecture existante.	PUID1441B	2023-01-21 16:22:05.461951	Gestion des fournisseurs de l’ERP Byblos	84	13	111
52	2022-01-01	2022-04-27	TELNET RSE (Réseau social d’entreprise TELNET) est une plateforme constituée de trois intervenants (Administrateur RH, le service de communication interne et les employés). Elle permet au service de communication interne et à l’administration RH de publier des évènements, des publications et des sondages et permet à ces employés de participer à ces évènements, faire réagir avec les publications et répondre aux sondages.	PUID1EBD9	2023-01-21 16:22:05.461951	Telnet RSE	47	69	116
\.


--
-- Data for Name: projet_more_sp; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.projet_more_sp (projet_id, project_more_sp) FROM stdin;
1	0
1	0
1	0
1	0
1	0
1	{"(0,0,0,0,0)"}
52	0
52	0
52	0
52	0
52	0
52	{"(0,0,0,0,0)"}
\.


--
-- Data for Name: projet_percentage_spc; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.projet_percentage_spc (projet_id, percentage_spc) FROM stdin;
\.


--
-- Data for Name: projet_sp_commitment; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.projet_sp_commitment (projet_id, project_sp_commitment) FROM stdin;
1	111
1	111
1	111
1	111
1	111
1	111
1	{"(111,111,111,111,111,111)"}
52	116
52	98
52	70
52	56
52	47
52	47
52	{"(116,98,70,56,47,47)"}
\.


--
-- Data for Name: projet_sp_completed; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.projet_sp_completed (projet_id, project_sp_completed) FROM stdin;
1	0
1	0
1	0
1	0
1	0
1	{"(0,0,0,0,0)"}
52	18
52	28
52	14
52	9
52	0
52	{"(18,28,14,9,0)"}
\.


--
-- Data for Name: roles; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.roles (id, name) FROM stdin;
1	ROLE_PRODUCTOWNER
2	ROLE_SCRUMMASTER
3	ROLE_DEVELOPER
\.


--
-- Data for Name: sprints; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.sprints (id, more_sp, s_reference, sdate_debut, sdate_fin, description, title, supdated_date, work_commitment, work_completed, projet_id) FROM stdin;
152	0	SUID17844	2022-01-20	2022-02-16	Dans ce sprint intitulé "Gestion des événements et des publications" nous avons visé la partie de gestion des événements et des publications au sein de TELNET	RSE Sprint 2	2023-01-21 16:22:05.439679	0	28	52
302	0	SUID15D16	2022-02-17	2022-03-02	Gérer les publication et les commentaires	RSE Sprint 3	2023-01-21 16:22:05.439679	0	14	52
202	0	SUID12D17	2022-03-03	2022-03-30	Dans ce sprint intitulé "Gestion des sondages" nous avons visé la partie de gestion des sondages au sein de TELNET	RSE Sprint 4	2023-01-21 16:22:05.439679	19	9	52
304	0	SUID18F04	2022-05-06	2022-05-28	la suite de la gestion des factures et la gestion des documents	Byblοss Sprint 4	2023-01-21 16:22:05.439679	11	8	1
3	0	SUID141EB	2022-05-29	2022-06-27	Export des données, traduction, personnalisation du dashboard et statistiques	Byblοs Sprint 5	2023-01-21 16:22:05.439679	20	5	1
252	0	SUID154EC	2022-03-31	2022-04-27	Dans ce sprint intitulé "Gestion des profiles" nous avons visé la partie de gestion des profiles des employés de TELNET	RSE Sprint 5	2023-01-21 16:22:05.439679	28	0	52
107	0	SUID18F06	2022-01-01	2022-01-19	Dans ce sprint intitulé "Gestion des utilisateurs" nous avons visé la partie de gestion des employés au sein de TELNET et suite à la réunion de planification du sprint 1, nous avons fixé la liste des tâches à réaliser et leurs estimations dans le Sprint Backlog.	RSE Sprint 1	2023-01-21 16:22:05.439679	0	18	52
303	0	SUID16D4B	2022-03-25	2022-04-15	Conception et réalisation des fonctionnalité ainsi que Gestion des fournisseurs et gestion des sous-traitants	Byblοs Sprint 2	2023-01-21 16:22:05.439679	17	0	1
2	0	SUID168B1	2022-04-16	2022-05-05	Gestion des contrats, facturations et documents des fournisseurs	Byblοss Sprint 3	2023-01-21 16:22:05.439679	17	0	1
1	0	SUID118F5	2022-03-06	2022-03-24	Mise en place de l’architecture, authentification, gestion des fournisseurs et gestion des sous-traitants	Byblοs Sprint 1	2023-01-21 16:22:05.439679	19	0	1
\.


--
-- Data for Name: story; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.story (id, plus_sp, priority, sp_completed, st_reference, description, title, story_point, stupdated_date, sprint_id) FROM stdin;
2	0	1	0	STUID1A273	L' authentification	Authentification	5	2023-01-21 16:22:05.442249	1
452	0	1	8	STUID1C8F3	En tant qu’administrateur RH je veux recevoir des notifications pour consulter les publications en attentes, En tant qu’administrateur RH je veux apercevoir une publication en attente, En tant qu’administrateur RH je veux valider une publication en attente, En tant qu’administrateur RH je veux réfuser une publication en attente, En tant qu’administrateur RH je veux aimer et commenter une publication	Gérer les publication et les commentaires espace administrateur	0	2023-01-21 16:22:05.442249	302
403	0	2	0	STUID14272	En tant qu’administrateur RH je veux gérer mes données personnelles dans mon profile,  En tant qu’Administrateur RH je veux consulter les profiles des autres employés, En tant que Com interne je veux consulter mon profile, En tant que Com interne RH je veux gérer mes données personnelles dans mon profile, En tant que Com interne je veux consulter les profiles des autres employés et en tant que Collaborateur je veux consulter mon profile	Gérer mon profile espace com interne	15	2023-01-21 16:22:05.442249	252
352	0	2	11	STUID17802	En tant que Collaborateur je veux participer a un événement,  En tant que Collaborateur je veux consulter la liste des participants de chaque événement et en tant qu’administrateur RH je veux gérer des publications	Gérer les évenements espace collaborateur	0	2023-01-21 16:22:05.442249	152
56	0	3	5	STUID1951D	Traduction	Traduction	4	2023-01-21 16:22:05.442249	3
252	0	1	7	STUID15359	En tant qu’administrateur RH je veux gérer les sites de TELNET	Gérer les sites de TELNET	0	2023-01-21 16:22:05.442249	107
456	0	1	0	STUID1F57D	la suite de la gestion des factures	la suite de la gestion des factures	6	2023-01-21 16:22:05.442249	304
356	0	2	0	STUID1E069	En tant qu’administrateur RH je veux recevoir des notifications pour consulter les sondages en attentes,  En tant qu’administrateur RH je veux valider un sondage en attente, En tant qu’administrateur RH je veux réfuser un sondage en attente, En tant qu’administrateur RH je veux répondre a un sondage, En tant qu’administrateur RH je veux consulter ie pourcentage de chaque réponse a chaque sondage	Gérer les notifications de sondage espace administrateur	11	2023-01-21 16:22:05.442249	202
1	0	2	0	STUID1C117	Mise en place de l’architecture de la nοuvelle sοlutiοn du mοdule gestion des\nfournisseurs.	Mise en place de l’architecture	14	2023-01-21 16:22:05.442249	1
457	0	2	8	STUID1DE58	La gestion des documents	La gestion des documents	5	2023-01-21 16:22:05.442249	304
53	0	2	0	STUID133F6	la gestion des factures	La gestion des factures	6	2023-01-21 16:22:05.442249	2
52	0	1	0	STUID1C9F0	Gestion des contrats	La gestion des contrats	11	2023-01-21 16:22:05.442249	2
55	0	1	0	STUID1837D	Export des données	Export des données	6	2023-01-21 16:22:05.442249	3
454	0	1	0	STUID1DE08	Conception et réalisation de ces fonctionnalité	Conception et réalisation de ces fonctionnalité	8	2023-01-21 16:22:05.442249	303
357	0	3	2	STUID19B86	En tant que Com interne je veux consulter la liste des sondages dans la page d’accueil, En tant que Com interne je veux répondre a un sondage, En tant que Collaborateur je veux consulter la liste des sondages dans la page d’accueil et En tant que Collaborateur je veux répondre a un sondage	Gestion des sondages espace Com intrene	5	2023-01-21 16:22:05.442249	202
404	0	3	0	STUID1BB3F	En tant que Collaborateur RH je veux gérer mes données personnelles dans mon profile, En tant que Collaborateur je veux consulter les profiles des autres employés,  En tant qu’Administrateur RH je veux consulter les recommandations des collaborateurs disponibles	Gérer mes données personnelles dans mon profile espace collaborateur	10	2023-01-21 16:22:05.442249	252
402	0	1	0	STUID141D2	En tant qu’administrateur RH je veux consulter mon profile	Consulter mon profile espace administrateur	3	2023-01-21 16:22:05.442249	252
303	0	1	17	STUID107C5	En tant qu’administrateur RH je veux gérer des événements, En tant qu’administrateur RH je veux recevoir des notifications pour consulter les événements en attentes, En tant qu’administrateur RH je veux apercevoir un événement en attente, En tant qu’administrateur RH je veux valider un événement en attente et En tant qu’administrateur RH je veux réfuser un événement en attente	Gérer des événements espace administrateur 	0	2023-01-21 16:22:05.442249	152
455	0	2	0	STUID1ABEF	Gérer les fοurnisseurs. - Gérer les sous-traitants	Gestion des fournisseurs et gestion des sous-traitants	9	2023-01-21 16:22:05.442249	303
302	0	2	11	STUID1F4D6	- En tant qu’administrateur RH je veux recevoir des notifications pour consulter les événements en attentes.\n- En tant qu’administrateur RH je veux apercevoir un événement en attente.\n-  En tant qu’administrateur RH je veux valider un événement en attente.\n- En tant qu’administrateur RH je veux participer a un événement.\n- En tant qu’administrateur RH je veux consulter la liste des participants de chaque événement	La gestion d'événements	0	2023-01-21 16:22:05.442249	107
102	0	2	0	STUID19E2E	Personnalisation du dashboard et statistiques	Personnalisation du dashboard et statistiques	10	2023-01-21 16:22:05.442249	3
453	0	2	6	STUID15CC3	En tant que Com interne je veux créer une publication, En tant que Com interne je veux consulter la liste de mes publications validées, En tant que Com interne je veux recevoir des notifications dés la validation de mes publications en attentes par l’administrateur RH, En tant que Com interne je veux aimer et commenter une publication et En tant que Com interne je veux consulter la liste des employés qui ont aimés une publication	Gérer les publication et les commentaires espace Com interne	0	2023-01-21 16:22:05.442249	302
355	0	1	7	STUID19016	En tant qu’administrateur RH je veux gérer des sondages	Gérer des sondages espace administrateur	3	2023-01-21 16:22:05.442249	202
\.


--
-- Data for Name: tasks; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tasks (id, bugs, estimation, status, t_reference, tdate_debut, tdate_fin, description, title, tsupdated_date, type_task, story_id) FROM stdin;
306	1	2	Completed	TUID1BC80	2022-02-12	2022-02-13	Ajouter les images et les noms des participants dans chaque événement dans la page d’accueil	Ajouter les images et les noms des participants dans chaque événement	2023-01-21 16:22:05.445984	Default_task	352
107	1	1	In_progress	TUID12DCF	2022-06-09	2022-06-09	Ajοuter le widget fοurnisseur à la base de dοnnées.	Ajοuter le widget fοurnisseur à la base de dοnnées.	2023-01-21 16:22:05.445984	Default_task	102
474	0	1	In_progress	TUID1A7AF	2022-05-16	2022-05-16	Créer le Dοcument DTΟ et le service et le service implémentatiοn.	Créer le Dοcument DTΟ et le service et le service implémentatiοn.	2023-01-21 16:22:05.445984	Default_task	457
305	3	2	Completed	TUID1747D	2022-02-10	2022-02-11	Implémenter les méthodes et les services nécessaires pour lister les participants de chaque événement	 lister les participants de chaque événement	2023-01-21 16:22:05.445984	Default_task	352
358	0	1	Scheduled	TUID1F20C	2022-04-06	2022-04-06	Tester la modification des données personnelles	Tester la modification des données personnelles.	2023-01-21 16:22:05.445984	Default_task	403
327	0	1	In_progress	TUID14771	2022-03-13	2022-03-13	Implémenter les méthodes et les services nécessaires pour recevoir des notifications	Implémenter les méthodes et les services nécessaires pour recevoir des notifications	2023-01-21 16:22:05.445984	Default_task	356
105	1	2	In_progress	TUID16B23	2022-06-05	2022-06-06	Créer la méthode de l’export dans la partie front-end	Créer la méthode de l’export dans la partie front-end	2023-01-21 16:22:05.445984	Default_task	55
468	0	2	Cancelled	TUID1EA4C	2022-04-14	2022-04-15	Intégrer les modules	Intégrer les modules	2023-01-21 16:22:05.445984	Default_task	455
323	0	1	Succeeded	TUID10E4F	2022-03-07	2022-03-07	Implémenter les méthodes et les services nécessaires pour lister les sondages disponibles et valides	Lister les sondages disponibles et valides	2023-01-21 16:22:05.445984	Default_task	355
471	0	2	Scheduled	TUID1859E	2022-05-10	2022-05-11	Créer les interfaces nécessaires.	Créer les interfaces nécessaires.	2023-01-21 16:22:05.445984	Default_task	456
453	0	1	Completed	TUID1F0EE	2022-02-24	2022-02-24	Tester l’action ""j’aime"" et l’ajout d’un commentaire	Tester l’action ""j’aime"" et l’ajout d’un commentaire	2023-01-21 16:22:05.445984	Default_task	452
338	0	2	Completed	TUID1B9C6	2022-03-27	2022-03-28	Implémenter les méthodes et les services nécessaires pour lister les sondages dans la page d’accueil, Implémenter les interfaces nécessaires pour l’affichage des sondages dans la page d’accueil	l’affichage des sondages dans la page d’accueil	2023-01-21 16:22:05.445984	Default_task	357
326	0	1	Scheduled	TUID156BD	2022-03-12	2022-03-12	Tester l’ajout et l’affichage des sondages et la suppression des sondages	Tester l’ajout et l’affichage des sondages et la suppression des sondages	2023-01-21 16:22:05.445984	Default_task	355
362	0	2	Scheduled	TUID1453B	2022-04-10	2022-04-11	Implémenter les méthodes et les services nécessaires pour ajouter des projets réalisés et consulter ie profile pour voir les changements	Ajouter des projets réalisés	2023-01-21 16:22:05.445984	Default_task	403
465	1	6	In_progress	TUID14A02	2022-04-04	2022-04-09	Implémenter les différentes méthodes nécessaires pour la gestion des sous-traitants.	Crud des sous-traitants	2023-01-21 16:22:05.445984	Default_task	455
337	0	1	In_progress	TUID17690	2022-03-26	2022-03-26	Tester l’action de répondre a un sondage	Tester l’action de répondre a un sondage	2023-01-21 16:22:05.445984	Default_task	357
355	0	1	Scheduled	TUID19CD8	2022-04-03	2022-04-03	Ajouter un bouton dans ie profile qui redirige vers une page de modification ou d’ajout des données personnelles	Gérer les données personnelles	2023-01-21 16:22:05.445984	Default_task	403
258	3	3	Completed	TUID1D593	2022-01-13	2022-01-15	Ajouter l’action de validation sur ie dashboard de l’administrateur RH, Implémenter les méthodes et les services nécessaires de validation des événements en attentes et Tester la validation des événements en attentes	Implémenter les méthodes et les services de validation des événements en attentes.	2023-01-21 16:22:05.445984	Default_task	302
335	0	1	In_progress	TUID1B675	2022-03-24	2022-03-24	Implémenter les méthodes et les services nécessaires pour lister les sondages dans la page d’accueil	lister les sondages dans la page d’accueil	2023-01-21 16:22:05.445984	Default_task	357
67	0	3	Cancelled	TUID1D6D0	2022-04-26	2022-04-28	Implémenter les méthodes de la partie front-end.	Implémenter les méthodes de la partie front-end.	2023-01-21 16:22:05.445984	Default_task	52
460	0	1	Completed	TUID11BE2	2022-02-25	2022-02-25	Implémenter les méthodes et les services nécessaires pour ajouter une publication	Implémenter les méthodes et les services nécessaires pour ajouter une publication	2023-01-21 16:22:05.445984	Default_task	453
371	0	1	Scheduled	TUID1E43B	2022-04-23	2022-04-23	Implémenter les méthodes et les services nécessaires pour ajouter des centres d’intérét	Ajouter des centres d’intérét	2023-01-21 16:22:05.445984	Default_task	404
368	0	2	Scheduled	TUID1C6F4	2022-04-19	2022-04-20	Implémenter les méthodes et les services nécessaires pour ajouter des données personnelles comme la photo de profile, date de naissance, etc...	Implémenter les méthodes et les services nécessaires pour ajouter des données personnelles	2023-01-21 16:22:05.445984	Default_task	404
260	1	2	Succeeded	TUID10D94	2022-01-20	2022-01-21	Implémenter les méthodes et les services nécessaires pour ajouter un événement	Implémenter les méthodes et les services nécessaires pour ajouter un événement	2023-01-21 16:22:05.445984	Default_task	303
367	0	1	Scheduled	TUID1D1B8	2022-04-18	2022-04-18	Ajouter un bouton dans ie profile qui redirige vers une page de modification ou d’ajout des données personnelles	Ajouter un bouton dans ie profile qui redirige vers une page de modification des données personnelles	2023-01-21 16:22:05.445984	Default_task	404
357	0	1	Scheduled	TUID1003B	2022-04-05	2022-04-05	Implémenter les méthodes et les services nécessaires pour mettre a jour des données personnelles	Implémenter les méthodes et les services nécessaires pour mettre a jour des données personnelles	2023-01-21 16:22:05.445984	Default_task	403
452	0	2	Completed	TUID1C051	2022-02-19	2022-02-20	Implémenter les interfaces nécessaires pour apercevoir une publication en attente, Ajouter l’action de validation sur ie dashboard de l’administrateur RH	Ajouter l’action de validation sur ie dashboard de l’administrateur RH	2023-01-21 16:22:05.445984	Default_task	452
365	0	2	Scheduled	TUID16CFF	2022-04-15	2022-04-16	Ajouter l’action sur les images et les noms des employés dans l’accueil pour rediriger vers ses profiles	Ajouter l’action sur les images et les noms de com interne	2023-01-21 16:22:05.445984	Default_task	403
70	3	5	In_progress	TUID1EC8F	2022-05-01	2022-05-05	Ecrire la requête permettant de recueillir les infοrmatiοns des factures à partir de plusieurs tables dans myBatis.	Recueillir les infοrmatiοns	2023-01-21 16:22:05.445984	Default_task	53
475	0	2	Completed	TUID1F65B	2022-05-22	2022-05-23	Implémenter la méthode de téléchargement	Implémenter la méthode de téléchargement	2023-01-21 16:22:05.445984	Default_task	457
302	1	2	Completed	TUID19510	2022-02-06	2022-02-07	Implémenter les méthodes et services nécessaires pour participer a un événement	Implémenter les méthodes et services nécessaires pour participer a un événement	2023-01-21 16:22:05.445984	Default_task	352
256	0	1	Succeeded	TUID17BCB	2022-01-11	2022-01-11	Implémenter les méthodes et les services nécessaires pour apercevoir les événements en attentes	Apercevoir les événements en attentes.	2023-01-21 16:22:05.445984	Default_task	302
477	1	3	Succeeded	TUID1FD33	2022-05-26	2022-05-28	Créer les différentes méthodes cotés front-end	Implementation des méthodes cotés front-end	2023-01-21 16:22:05.445984	Default_task	457
111	1	1	In_progress	TUID11326	2022-06-20	2022-06-20	Utiliser le microservice PARAM pour récupérer la liste des champs traduits.	Récupérer la liste des champs traduit	2023-01-21 16:22:05.445984	Default_task	56
104	0	1	Scheduled	TUID1558A	2022-06-04	2022-06-04	Ajοuter l’οptiοn expοrter dans les différentes interfaces de gestiοn de cοntrats et de factures	Ajοuter l’οptiοn expοrter dans UI	2023-01-21 16:22:05.445984	Default_task	55
333	0	2	Scheduled	TUID13C8F	2022-03-20	2022-03-21	Impiémenter les méthodes et services nécessaires pour répondre a un sondage, Ajouter un popup qui contient les réponse a un sondage et Ajouter l’action pour choisir une réponse	Répondre a un sondage	2023-01-21 16:22:05.445984	Default_task	356
369	0	1	Scheduled	TUID14277	2022-04-21	2022-04-21	Implémenter les méthodes et les services nécessaires pour mettre a jour des données personnelles	Mettre a jour des données personnelles	2023-01-21 16:22:05.445984	Default_task	404
331	0	2	In_progress	TUID11F85	2022-03-17	2022-03-18	Ajouter l’action de réfusion sur ie dashboard de l’administrateur RH, Implémenter les méthodes et les services nécessaires de réfusion des sondages en attentes	Ajouter l’action de réfusion sur ie dashboard 	2023-01-21 16:22:05.445984	Default_task	356
457	0	1	Succeeded	TUID1BC5C	2022-02-23	2022-02-23	Ajouter l’action ""j’aime"" et ""commentaire""  dans chaque publication dans la page d’accueil"	Ajouter l’action ""j’aime"" et ""commentaire""	2023-01-21 16:22:05.445984	Default_task	452
252	1	2	Succeeded	TUID1F8E3	2022-01-03	2022-01-04	Implémenter les méthodes et services nécessaires pour lister les sites de TELNET disponibles	lister les sites de TELNET disponibles	2023-01-21 16:22:05.445984	Default_task	252
261	1	2	Succeeded	TUID16ACC	2022-01-22	2022-01-23	Implémenter les méthodes et les services nécessaires pour apercevoir un événément avant son ajout	Apercevoir un événément avant son ajout	2023-01-21 16:22:05.445984	Default_task	303
1	0	1	Scheduled	TUID181D3	2022-03-06	2022-03-06	- Ajοuter la cοuche Spring Security à nοtre microservice.	Ajοuter la cοuche Spring Security	2023-01-21 16:22:05.445984	Default_task	2
259	1	3	Completed	TUID10F60	2022-01-16	2022-01-19	Implémenter les méthodes et services nécessaires pour participer a un événement, Ajouter l’action de participation sur chaque événement dans la page d’accueil et Implémenter les méthodes et les services nécessaires pour lister les participants de chaque événement	Implémenter les méthodes et services pour participer a un événement	2023-01-21 16:22:05.445984	Default_task	302
363	0	2	Scheduled	TUID17C79	2022-04-12	2022-04-13	Ajouter l’action sur les images et les noms des employés dans l’accueil pour rediriger vers ses profiles	Ajouter l’action sur les images et les noms des employés	2023-01-21 16:22:05.445984	Default_task	403
469	0	2	In_progress	TUID1CB60	2022-04-10	2022-04-11	Créer les interfaces pour la gestion des sous-traitants	Créer les interfaces pour la gestion des sous-traitants	2023-01-21 16:22:05.445984	Default_task	455
109	2	4	In_progress	TUID1272B	2022-06-13	2022-06-16	Afficher le widget fοurnisseur , le déplacer , le supprimer du dashbοard en consommant les api fournis par le microservice PARAM	Manage widget fοurnisseur	2023-01-21 16:22:05.445984	Default_task	102
375	0	2	Scheduled	TUID1B913	2022-04-26	2022-04-27	Implémenter les méthodes et les services nécessaires pour regrouper les empoyés qui ont les mémes\ncompétences et centres d’intérét	regrouper les empoyés qui ont les mémes compétences et centres d’intérét pour les com interne	2023-01-21 16:22:05.445984	Default_task	404
263	0	5	Completed	TUID14EB4	2022-01-25	2022-01-29	Implémenter les interfaces nécessaires pour l’affichage,l’ajout et la modification d’un événément, Tester l’ajout et l’attichage des événements, Implémenter les méthodes et les services nécessaires de modification dev événements.\nAjouter l’action de suppression sur la page de consultation de la liste des événements.\nImplémenter les méthodes et les services nécessaires de suppression des\névénements et Tester la suppression des événements.	L'affichage l’ajout et la modification d’un événément	2023-01-21 16:22:05.445984	Default_task	303
476	0	2	Scheduled	TUID19F05	2022-05-24	2022-05-25	Créer l’interface à travers laquelle nous allons pouvoir manipuler les documents.	Manipuler les documents	2023-01-21 16:22:05.445984	Default_task	457
454	0	1	Completed	TUID12BF0	2022-02-17	2022-02-17	Implémenter les méthodes et les services nécessaires pour recevoir des notifications	Recevoir des notifications	2023-01-21 16:22:05.445984	Default_task	452
361	0	1	Scheduled	TUID17B33	2022-04-09	2022-04-09	Tester l’ajout des compétences et l’ajout des centres d’intérét.	Tester l’ajout des compétences et l’ajout des centres d’intérét	2023-01-21 16:22:05.445984	Default_task	403
374	0	1	Scheduled	TUID1C4DF	2022-04-25	2022-04-25	Consulter les recommandations disponibles dans la page d’accueil	Consulter les recommandations disponibles dans la page d’accueil	2023-01-21 16:22:05.445984	Default_task	404
329	0	1	In_progress	TUID1F01A	2022-03-15	2022-03-15	Implémenter les méthodes et les services nécessaires de validation des sondages en attentes	validation des sondages en attentes	2023-01-21 16:22:05.445984	Default_task	356
106	0	2	Scheduled	TUID120B2	2022-06-07	2022-06-08	Ajouter au menu de l’application tous les liens utiles pour naviguer dans le module fournisseur	Ajouter au menu de l’application tous les liens utiles	2023-01-21 16:22:05.445984	Default_task	102
264	0	6	Completed	TUID1E310	2022-01-30	2022-02-05	Implémenter les méthodes et les services nécessaires pour recevoir des notifications. Implémenter les méthodes et les services nécessaires pour apercevoir les événements en attentes. Implémenter les interfaces nécessaires pour apercevoir un événement en attente. Ajouter l’action de validation sur ie dashboard de l’administrateur RH. Implémenter les méthodes et les services nécessaires de validation des événements en attentes. Teser la validation des événements en attentes. Ajouter l’action de rétusion sur ie dashboard de l’administrateur RH	valider, réfuser et apercevoir les  événements en attente	2023-01-21 16:22:05.445984	Default_task	303
470	0	4	Scheduled	TUID1381A	2022-05-06	2022-05-09	Implémenter les méthοdes nécessaires pοur la cοnsultatiοn en exécutant la requête et la mοdificatiοn d’une facture et la recherche multicritères.	Implémenter les méthοdes de consultation et de recherche	2023-01-21 16:22:05.445984	Default_task	456
464	1	2	In_progress	TUID17617	2022-04-01	2022-04-02	Intégrer les modules	Intégrer les modules	2023-01-21 16:22:05.445984	Default_task	454
322	0	2	Completed	TUID1667A	2022-03-05	2022-03-06	Implémenter les méthodes et les services nécessaires pour apercevoir un sondage avant son ajout	Apercevoir un sondage avant son ajout	2023-01-21 16:22:05.445984	Default_task	355
69	0	1	Scheduled	TUID1AA8A	2022-04-30	2022-04-30	Créer le repository, les DTOs, les services et leurs implémentation et les contrôleurs	Créer le repository, les DTOs, les services 	2023-01-21 16:22:05.445984	Default_task	53
472	0	4	Failed	TUID1F269	2022-05-12	2022-05-15	Implémenter les méthοdes de la partie frοnt-end.	Implémenter les méthοdes de la partie frοnt-end.	2023-01-21 16:22:05.445984	Default_task	456
467	0	2	Cancelled	TUID14CA5	2022-04-12	2022-04-13	Implémenter les méthodes et le contrôle de saisie .	Implémenter les méthodes et le contrôle de saisie .	2023-01-21 16:22:05.445984	Default_task	455
253	0	2	Succeeded	TUID18F5D	2022-01-05	2022-01-06	Implémenter les interfaces nécessaires pour l’affichage, l’ajout et la modification d’un site	Crud de site	2023-01-21 16:22:05.445984	Default_task	252
458	0	1	Completed	TUID1E167	2022-02-26	2022-02-26	implémenter les interfaces nécessaires pour l’ajout d’une publication, Implémenter les méthodes et les services nécessaires pour apercevoir une publication avant son ajout.	l’ajout d’une publication et  apercevoir une publication avant son ajout	2023-01-21 16:22:05.445984	Default_task	453
461	0	1	Succeeded	TUID1399E	2022-03-01	2022-03-01	Implémenter les méthodes et les services nécessaires pour recevoir des notifications.	Recevoir des notifications	2023-01-21 16:22:05.445984	Default_task	453
308	0	1	Succeeded	TUID15342	2022-02-15	2022-02-15	Implémenter les méthodes et les services nécessaires pour apercevoir une publication avant son ajout, Implémenter les méthodes et les services nécessaires pour lister les publications disponibles et valides	lister les publications disponibles et valides et  apercevoir une publication avant son ajout	2023-01-21 16:22:05.445984	Default_task	352
364	0	1	Scheduled	TUID16466	2022-04-14	2022-04-14	Implémenter les méthodes et les services nécessaires pour récupérer ie profile de chaque employé	Récupérer ie profile de chaque employé	2023-01-21 16:22:05.445984	Default_task	403
324	0	2	In_progress	TUID1E3D5	2022-03-08	2022-03-09	Implémenter les interfaces nécessaires pour l’affichage,l’ajout et la modification d’un sondage	L’affichage,l’ajout et la modification d’un sondage	2023-01-21 16:22:05.445984	Default_task	355
352	0	1	Scheduled	TUID15715	2022-03-31	2022-03-31	Implémenter les méthodes et les services nécessaires pour récupérer les données de l’employé	Récupérer les données de l’employé	2023-01-21 16:22:05.445984	Default_task	402
463	0	6	Scheduled	TUID1C3A6	2022-03-25	2022-03-31	Créer les méthodes dans la partie front-end en utilisant un shared-service pour cοnserver les informations lorsqu’on navigue entre compοsants.	Créer les méthodes dans la partie front-end	2023-01-21 16:22:05.445984	Default_task	454
330	0	1	In_progress	TUID11E0A	2022-03-16	2022-03-16	Tester la validation des sondages en attentes	Tester la validation des sondages en attentes	2023-01-21 16:22:05.445984	Default_task	356
478	2	3	Completed	TUID17F09	2022-05-19	2022-05-21	Créer la méthode qui retourne une miniature d’un document enregistré ( en consommant les services offerts par GED )	Miniature d’un document enregistré	2023-01-21 16:22:05.445984	Default_task	457
473	1	2	In_progress	TUID187A0	2022-05-17	2022-05-18	Créer la méthοde uplοad du dοcument qui utilise le micrοservice GED qui nοus fournit le GED Path qu’on doit persister dans la base avec les informations du document.	Créer la méthοde uplοad du dοcument 	2023-01-21 16:22:05.445984	Default_task	457
354	0	1	Scheduled	TUID1433A	2022-04-02	2022-04-02	Implémenter les méthodes et les services nécessaires pour réagir avec les publications dans ie profile	Réagir avec les publications	2023-01-21 16:22:05.445984	Default_task	402
359	0	1	Scheduled	TUID11A47	2022-04-07	2022-04-07	Implémenter les méthodes et les services nécessaires pour ajouter des compétences	Ajouter des compétences	2023-01-21 16:22:05.445984	Default_task	403
370	0	1	Scheduled	TUID1467C	2022-04-22	2022-04-22	Implémenter les méthodes et les services nécessaires pour ajouter des compétences	Ajouter des compétences	2023-01-21 16:22:05.445984	Default_task	404
113	0	2	Failed	TUID1CC09	2022-06-26	2022-06-27	Implémenter la traductiοn dans tοutes les interfaces de l’applicatiοn.	Implémenter la traductiοn dans tοutes les interfaces de l’applicatiοn.	2023-01-21 16:22:05.445984	Default_task	56
202	1	2	Succeeded	TUID1D149	2022-01-01	2022-01-02	Implémenter les méthodes et services nécessaires pour l’ajout d’un nouveau site	Ajouter un nouveau site	2023-01-21 16:22:05.445984	Default_task	252
328	0	1	In_progress	TUID14FCD	2022-03-14	2022-03-14	Ajouter l’action de validation sur ie dashboard de l’administrateur RH	Ajouter l’action de validation sur ie dashboard 	2023-01-21 16:22:05.445984	Default_task	356
56	1	3	In_progress	TUID141E6	2022-03-22	2022-03-24	Implémenter les méthodes de cοntrôle de saisie pour les formulaires.	Les méthodes de cοntrôle de saisie	2023-01-21 16:22:05.445984	Default_task	1
356	0	1	Scheduled	TUID127C5	2022-04-04	2022-04-04	Implémenter les méthodes et les services nécessaires pour ajouter des données personnelles comme la photo de profile, date de naissance, etc...	Implémenter les méthodes et les services nécessaires pour ajouter des données personnelles	2023-01-21 16:22:05.445984	Default_task	403
466	2	1	In_progress	TUID130AB	2022-04-03	2022-04-03	Créer les repositories, DTOs, les services et les contrôleurs des sous-traitants	Créer les repositories, DTOs, les services et les contrôleurs.	2023-01-21 16:22:05.445984	Default_task	455
307	0	1	Completed	TUID1FD14	2022-02-14	2022-02-14	Implémenter les méthodes et les services nécessaires pour ajouter une publication	Ajouter une publication	2023-01-21 16:22:05.445984	Default_task	352
55	0	2	Scheduled	TUID1AE80	2022-03-20	2022-03-21	Créer les interfaces de cοnsultation, de recherche et les formulaires d’ajout et de modification.	La création des interfaces UI de crud	2023-01-21 16:22:05.445984	Default_task	1
459	0	1	Succeeded	TUID1A27A	2022-03-02	2022-03-02	Implémenter les méthodes et services nécessaires pour aimer et commenter une publication, Ajouter l’action ""j’aime"" et  ""commentaire""  dans chaque publication dans la page d’accueil et tester l’action ""j’aime"" et commentaire	Ajouter l’action ""j’aime"" et  ""commentaire"" 	2023-01-21 16:22:05.445984	Default_task	453
325	0	2	Succeeded	TUID1A8E3	2022-03-10	2022-03-11	Ajouter l’action de suppression sur la page de consultation de la liste des sondages, Implémenter les méthodes et les services nécessaires de suppression des sondages	Ajouter l’action de suppression sur la page de consultation de la liste des sondages	2023-01-21 16:22:05.445984	Default_task	355
304	0	1	Completed	TUID11EF4	2022-02-09	2022-02-09	Tester la participation	Tester la participation	2023-01-21 16:22:05.445984	Default_task	352
262	0	2	Succeeded	TUID1426C	2022-01-24	2022-01-25	Implémenter les méthodes et les services nécessaires pour lister les événements disponibles et valides	 lister les événements disponibles et valides	2023-01-21 16:22:05.445984	Default_task	303
332	0	1	Scheduled	TUID1E10B	2022-03-19	2022-03-19	Tester la réfusion des sondages en attentes	Tester la réfusion des sondages en attentes	2023-01-21 16:22:05.445984	Default_task	356
339	0	2	Scheduled	TUID1263E	2022-03-29	2022-03-30	Implémenter les méthodes et les services nécessaires pour répondre a un sondage et tester l’action de répondre a un sondage	Implémenter les méthodes de répondre a un sondage et tester l’action de répondre a un sondage	2023-01-21 16:22:05.445984	Default_task	357
255	1	3	Succeeded	TUID1F98A	2022-01-08	2022-01-10	Impiémenter les méthodes et les services nécessaires pour recevoir des notifications.	Recevoir les notifications	2023-01-21 16:22:05.445984	Default_task	302
336	0	1	In_progress	TUID15A7C	2022-03-25	2022-03-25	Implémenter les interfaces nécessaires pour l’affichage des sondages dans la page d’accueil	l’affichage des sondages dans la page d’accueil	2023-01-21 16:22:05.445984	Default_task	357
108	1	3	Scheduled	TUID1B421	2022-06-10	2022-06-12	Créer des statistiques qui vοnt être affichées dans le widget fοurnisseur.	Créer des statistiques	2023-01-21 16:22:05.445984	Default_task	102
456	0	1	Completed	TUID13439	2022-02-18	2022-02-18	Implémenter les méthodes et les services nécessaires pour apercevoir les publications en attentes	Apercevoir les publications en attentes	2023-01-21 16:22:05.445984	Default_task	452
455	0	2	Succeeded	TUID1F3C6	2022-02-22	2022-02-23	Tester la validation des publications en attentes, Ajouter l’action de rétusion sur ie dashboard de l’administrateur RH et Tester la ré tusion des publications en attentes	Tester la validation des publications en attentes	2023-01-21 16:22:05.445984	Default_task	452
254	0	1	Succeeded	TUID17D43	2022-01-07	2022-01-07	Tester les crud de site	Tester les crud de site	2023-01-21 16:22:05.445984	Default_task	252
53	0	1	Scheduled	TUID1CC1C	2022-03-11	2022-03-11	Créer les éléments définissant l’architecture logique de la partie back-end (repositοry, DTΟ, service, service implémentatiοn et cοntrôleur)	définir l'architecture logique de la partie back-end	2023-01-21 16:22:05.445984	Default_task	1
64	0	1	Scheduled	TUID1EDC4	2022-04-16	2022-04-16	Créer les éléments définissant l’architecture logique de la partie back-end ( contrôleur , DTO , service, service implémentatiοn et repοsitοry )	l’architecture logique de la partie back-end des contrats	2023-01-21 16:22:05.445984	Default_task	52
65	2	8	In_progress	TUID1D69E	2022-04-17	2022-04-23	Implémenter les méthοdes ajοuter, modifier, consulter et rechercher en respectant l’architecture définie précédemment.	CRUD methode pour les contrats	2023-01-21 16:22:05.445984	Default_task	52
68	0	1	Failed	TUID118E9	2022-04-29	2022-04-29	Implémenter les méthodes de contrôle de saisie pour les formulaires.	Implémenter les méthodes de contrôle de saisie pour les formulaires.	2023-01-21 16:22:05.445984	Default_task	52
112	1	5	Succeeded	TUID1E316	2022-06-21	2022-06-25	Implémenter la traductiοn dans la partie frοnt-end en utilisant le service de translatiοn de ngx-translate d’Angular.	Implémenter la traductiοn dans la partie frοnt-end 	2023-01-21 16:22:05.445984	Default_task	56
257	1	1	Completed	TUID1599F	2022-01-12	2022-01-12	Implémenter les interfaces nécessaires pour apercevoir un événement en attente	Implémenter les interfaces nécessaires pour apercevoir un événement	2023-01-21 16:22:05.445984	Default_task	302
360	0	1	Scheduled	TUID14490	2022-04-08	2022-04-08	Implémenter les méthodes et les services nécessaires pour ajouter des centres d’intérét	Ajouter des centres d’intérét	2023-01-21 16:22:05.445984	Default_task	403
54	2	8	In_progress	TUID16FDC	2022-03-12	2022-03-19	Implémenter les méthodes ajouter , modifier, cοnsulter et rechercher en respectant l’architecture définie précédemment.	Implémenter CRUD	2023-01-21 16:22:05.445984	Default_task	1
103	1	3	In_progress	TUID19C54	2022-05-30	2022-06-03	Créer la méthοde d’expοrt dans la partie Back-end qui permet d’expοrter une liste de dοnnées en respectant l’architecture lοgicielle	Créer la méthοde d’expοrt dans la partie Back-end	2023-01-21 16:22:05.445984	Default_task	55
321	0	2	Completed	TUID16051	2022-03-03	2022-03-04	Implémenter les méthodes et les services nécessaires pour ajouter un sondage	Ajouter un sondage	2023-01-21 16:22:05.445984	Default_task	355
303	0	1	Completed	TUID1C53B	2022-02-08	2022-02-08	Ajouter l’action de participation dans chaque événement dans la page d’accueil	Ajouter l’action de participation dans chaque événement	2023-01-21 16:22:05.445984	Default_task	352
309	0	1	Succeeded	TUID1B042	2022-02-16	2022-02-16	Implémenter les interfaces nécessaires pour l’affichage,l’ajout et la modification d’une publication et tester l'ajout et l’attichage des\npublications	Crud de publication	2023-01-21 16:22:05.445984	Default_task	352
110	0	3	Scheduled	TUID1CCA2	2022-06-17	2022-06-19	Créer les scripts de la traduction de tous les champs de l’application et l’exécuter dans la base	Créer les scripts de la traduction	2023-01-21 16:22:05.445984	Default_task	56
66	0	2	Scheduled	TUID1A887	2022-04-24	2022-04-25	Créer les interfaces de consultation, de recherche et les formulaires d’ajout et de modification.	Créer les interfaces 	2023-01-21 16:22:05.445984	Default_task	52
366	0	1	Scheduled	TUID11E9B	2022-04-17	2022-04-17	Implémenter les méthodes et les services nécessaires pour récupérer le profile de chaque employé	Consulter les profiles des autres employés espace  Com interne	2023-01-21 16:22:05.445984	Default_task	403
462	0	2	Succeeded	TUID1C095	2022-02-27	2022-02-28	Implémenter les méthodes et les services nécessaires pour lister les publications valides, Implémenter les interfaces nécessaires pour l’affichage des publications.	lister et afficher  les publications valides	2023-01-21 16:22:05.445984	Default_task	453
334	0	2	Scheduled	TUID1D792	2022-03-22	2022-03-23	Implémenter les méthodes et les services nécessaires pour calculer le pourcentage de chaque réponse a\nchaque sondage et Ajouter les barres qui contients les pourcentages pour chaque réponse	Calculer le pourcentage de chaque réponse a chaque sondage 	2023-01-21 16:22:05.445984	Default_task	356
373	0	1	Scheduled	TUID19855	2022-04-24	2022-04-24	Implémenter les méthodes et les services nécessaires pour regrouper les empoyés qui ont les mémes compétences et centres d’intérét	Regrouper les empoyés qui ont les mémes compétences et centres d’intérét	2023-01-21 16:22:05.445984	Default_task	404
353	0	1	Scheduled	TUID12187	2022-04-01	2022-04-01	Implémenter les méthodes et les services nécessaires pour récupérer les\npublications de l’employé	Récupérer les publications de l’employé	2023-01-21 16:22:05.445984	Default_task	402
52	1	4	In_progress	TUID17BF7	2022-03-07	2022-03-10	Intégrer le module authentification avec notre module coté Front-end.	Intégrer le module authentification	2023-01-21 16:22:05.445984	Default_task	2
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (id, email, password, username) FROM stdin;
2	yassine@scrumm.tn	$2a$10$RwsF0IKAjm5suasoW8BpxebmozVxgbHO1AgDoQmJiNYrLYB9stEey	yassine
1	chamsEddine@productowner.tn	$2a$10$rgdC0VKEKOIRpVZOMSxXEOJDu/sOKoDwuV4h9ADwnLYQraTNDIwYS	chamsEddine
3	iheb@developer.tn	$2a$10$xXH7kKAeqnnjXLua5g0uL.NO7OlMQ2jRDJNNlDac5L6i/ttRDzpk6	iheb
4	tasnime@developer.tn	$2a$10$INhF.YwdDL.YaV.piJl5uORlkpYJ54suOrv6xi4NVFmL7fo2giY0q	tasnime
52	amine@developer.tn	$2a$10$dc2JGWksvM2o4HPyzWhtDuUuwAWPFZo9uRVsTlXdUw5kwYG8LoUIi	amine
53	mohamed@developer.tn	$2a$10$VY9z0bz9KEqAvUeGxSi.lue7l2fZnn03X7SYJHi3A9YBzjBRwkMY.	mohamed
54	syrine@scrumm.tn	$2a$10$HMZugsILoM8SeeAb8Kkz6euX/Y8jx1NO243A3w/jT.n1uuu51laUO	syrine
55	aymen@productowner.tn	$2a$10$ZItm/MeY3dwJ1QKfNCEtM.ZkCOh6QZx2OGK.88KSUgdw/QazHm/b2	aymen
\.


--
-- Data for Name: users_projects; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users_projects (user_id, project_id) FROM stdin;
52	4
52	1
52	3
52	2
\.


--
-- Data for Name: users_roles; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users_roles (user_id, role_id) FROM stdin;
1	1
2	2
3	3
4	3
52	3
53	3
54	2
55	1
\.


--
-- Data for Name: users_tasks; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users_tasks (users_id, tasks_id) FROM stdin;
202	53
202	54
202	52
202	55
466	2
466	4
466	3
466	1
327	52
327	53
327	3
327	4
\.


--
-- Data for Name: workedl_sprints; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.workedl_sprints (sprint_id, workedl_array) FROM stdin;
3	27
3	27
3	26
3	25
3	24
3	24
3	24
3	24
3	20
3	19
3	17
3	15
107	18
107	17
107	16
107	16
107	16
107	15
107	14
107	13
107	12
107	10
107	8
107	7
107	5
107	4
107	3
107	2
107	{"(18,17,16,16,16,15,14,13,12,10,8,7,5,4,3,2)"}
2	44
2	43
2	43
2	40
2	39
2	39
2	39
2	38
2	38
2	37
2	37
2	35
2	34
2	33
2	30
2	29
2	28
2	27
2	26
2	24
2	20
2	18
2	16
2	{"(44,43,43,40,39,39,39,38,38,37,37,35,34,33,30,29,28,27,26,24,20,18,16)"}
3	14
3	13
3	12
3	11
3	10
3	9
3	8
3	7
3	6
3	5
3	4
3	3
3	2
3	{"(27,27,26,25,24,24,24,24,20,19,17,15,14,13,12,11,10,9,8,7,6,5,4,3,2)"}
1	40
1	39
1	38
1	37
1	37
1	37
1	37
1	35
1	34
1	33
1	32
1	31
1	30
1	29
1	29
1	29
1	29
1	25
1	23
1	18
1	15
1	10
1	8
1	6
1	4
1	{"(40,39,38,37,37,37,37,35,34,33,32,31,30,29,29,29,29,25,23,18,15,10,8,6,4)"}
152	28
152	28
152	27
152	26
152	25
152	25
152	24
152	23
152	21
152	20
152	18
152	18
152	17
152	16
152	15
152	13
152	11
152	{"(28,28,27,26,25,25,24,23,21,20,18,18,17,16,15,13,11)"}
\.


--
-- Name: byview_generator; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.byview_generator', 2455301, true);


--
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.hibernate_sequence', 3, true);


--
-- Name: jcoverage_generator; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.jcoverage_generator', 201, true);


--
-- Name: project_generator; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.project_generator', 101, true);


--
-- Name: sprint_generator; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.sprint_generator', 351, true);


--
-- Name: story_generator; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.story_generator', 501, true);


--
-- Name: task_generator; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.task_generator', 551, true);


--
-- Name: user_generator; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.user_generator', 101, true);


--
-- Name: by_ref_view by_ref_view_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.by_ref_view
    ADD CONSTRAINT by_ref_view_pkey PRIMARY KEY (id);


--
-- Name: jcoverage jcoverage_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.jcoverage
    ADD CONSTRAINT jcoverage_pkey PRIMARY KEY (id);


--
-- Name: projects projects_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.projects
    ADD CONSTRAINT projects_pkey PRIMARY KEY (id);


--
-- Name: roles roles_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.roles
    ADD CONSTRAINT roles_pkey PRIMARY KEY (id);


--
-- Name: sprints sprints_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sprints
    ADD CONSTRAINT sprints_pkey PRIMARY KEY (id);


--
-- Name: story story_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.story
    ADD CONSTRAINT story_pkey PRIMARY KEY (id);


--
-- Name: tasks tasks_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tasks
    ADD CONSTRAINT tasks_pkey PRIMARY KEY (id);


--
-- Name: users uk6dotkott2kjsp8vw4d0m25fb7; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT uk6dotkott2kjsp8vw4d0m25fb7 UNIQUE (email);


--
-- Name: by_ref_view ukaa7i3v2l1mf1jyfbb1wh96ksk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.by_ref_view
    ADD CONSTRAINT ukaa7i3v2l1mf1jyfbb1wh96ksk UNIQUE (ref_task);


--
-- Name: projects ukcbuxca6n8mnfutng2kvec11oy; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.projects
    ADD CONSTRAINT ukcbuxca6n8mnfutng2kvec11oy UNIQUE (p_reference);


--
-- Name: tasks ukd2tqpjgbj1wco4sk5katf0kr5; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tasks
    ADD CONSTRAINT ukd2tqpjgbj1wco4sk5katf0kr5 UNIQUE (t_reference);


--
-- Name: story ukiknbwta104re0t2q0s1vmi13e; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.story
    ADD CONSTRAINT ukiknbwta104re0t2q0s1vmi13e UNIQUE (st_reference);


--
-- Name: sprints ukmwl4poarb5vvhhqox2srryr3c; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sprints
    ADD CONSTRAINT ukmwl4poarb5vvhhqox2srryr3c UNIQUE (s_reference);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- Name: users_projects users_projects_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users_projects
    ADD CONSTRAINT users_projects_pkey PRIMARY KEY (user_id, project_id);


--
-- Name: users_roles users_roles_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users_roles
    ADD CONSTRAINT users_roles_pkey PRIMARY KEY (user_id, role_id);


--
-- Name: users_tasks users_tasks_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users_tasks
    ADD CONSTRAINT users_tasks_pkey PRIMARY KEY (users_id, tasks_id);


--
-- Name: projet_sp_completed fk1cl3fajp1ijclwfo230792680; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.projet_sp_completed
    ADD CONSTRAINT fk1cl3fajp1ijclwfo230792680 FOREIGN KEY (projet_id) REFERENCES public.projects(id);


--
-- Name: projet_percentage_spc fk1q8o5slnwafji6hjxyfo7cgf0; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.projet_percentage_spc
    ADD CONSTRAINT fk1q8o5slnwafji6hjxyfo7cgf0 FOREIGN KEY (projet_id) REFERENCES public.projects(id);


--
-- Name: users_roles fk2o0jvgh89lemvvo17cbqvdxaa; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users_roles
    ADD CONSTRAINT fk2o0jvgh89lemvvo17cbqvdxaa FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- Name: ideall_sprints fk68kcgkuwus15n9crgew4chb2x; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ideall_sprints
    ADD CONSTRAINT fk68kcgkuwus15n9crgew4chb2x FOREIGN KEY (sprint_id) REFERENCES public.sprints(id);


--
-- Name: projet_sp_commitment fk77xbobs1hgyv9rv23d4mh70w2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.projet_sp_commitment
    ADD CONSTRAINT fk77xbobs1hgyv9rv23d4mh70w2 FOREIGN KEY (projet_id) REFERENCES public.projects(id);


--
-- Name: story fk7cya23djg503d72wl6ps6t7yx; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.story
    ADD CONSTRAINT fk7cya23djg503d72wl6ps6t7yx FOREIGN KEY (sprint_id) REFERENCES public.sprints(id) ON DELETE CASCADE;


--
-- Name: member_of_project fkdehjegl4dc57cqus3rp09n1nb; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.member_of_project
    ADD CONSTRAINT fkdehjegl4dc57cqus3rp09n1nb FOREIGN KEY (project_id) REFERENCES public.projects(id);


--
-- Name: users_projects fkdu1rvj9ou5rwdvjblv3ixj6fl; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users_projects
    ADD CONSTRAINT fkdu1rvj9ou5rwdvjblv3ixj6fl FOREIGN KEY (project_id) REFERENCES public.users(id);


--
-- Name: tasks fkf87etkkjurkmll09duygb67u; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tasks
    ADD CONSTRAINT fkf87etkkjurkmll09duygb67u FOREIGN KEY (story_id) REFERENCES public.story(id) ON DELETE CASCADE;


--
-- Name: projet_more_sp fkhn6m8oahkuh2x6s341rn0trpj; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.projet_more_sp
    ADD CONSTRAINT fkhn6m8oahkuh2x6s341rn0trpj FOREIGN KEY (projet_id) REFERENCES public.projects(id);


--
-- Name: sprints fkisgi4rqj90eq2v3egw5vqi9qh; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sprints
    ADD CONSTRAINT fkisgi4rqj90eq2v3egw5vqi9qh FOREIGN KEY (projet_id) REFERENCES public.projects(id) ON DELETE CASCADE;


--
-- Name: users_roles fkj6m8fwv7oqv74fcehir1a9ffy; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users_roles
    ADD CONSTRAINT fkj6m8fwv7oqv74fcehir1a9ffy FOREIGN KEY (role_id) REFERENCES public.roles(id);


--
-- Name: workedl_sprints fkko1x787t2cdf7unwefog3yd7d; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.workedl_sprints
    ADD CONSTRAINT fkko1x787t2cdf7unwefog3yd7d FOREIGN KEY (sprint_id) REFERENCES public.sprints(id);


--
-- Name: member_of_task fkkrmk0byvfk6f0c70pb80a1uvh; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.member_of_task
    ADD CONSTRAINT fkkrmk0byvfk6f0c70pb80a1uvh FOREIGN KEY (task_attached_id) REFERENCES public.tasks(id);


--
-- Name: users_projects fkmkhxxe2bpri6rhtkv66ynh0dl; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users_projects
    ADD CONSTRAINT fkmkhxxe2bpri6rhtkv66ynh0dl FOREIGN KEY (user_id) REFERENCES public.projects(id);


--
-- Name: users_tasks fkn7i7ouuwvr3m4p8o0m0wsdt14; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users_tasks
    ADD CONSTRAINT fkn7i7ouuwvr3m4p8o0m0wsdt14 FOREIGN KEY (tasks_id) REFERENCES public.users(id);


--
-- Name: users_tasks fkp86j85jqsry805u806pqkxf6m; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users_tasks
    ADD CONSTRAINT fkp86j85jqsry805u806pqkxf6m FOREIGN KEY (users_id) REFERENCES public.tasks(id);


--
-- Name: days_sprints fkpkw1mcie4i1qdur0nr11hon1l; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.days_sprints
    ADD CONSTRAINT fkpkw1mcie4i1qdur0nr11hon1l FOREIGN KEY (sprint_id) REFERENCES public.sprints(id);


--
-- PostgreSQL database dump complete
--

