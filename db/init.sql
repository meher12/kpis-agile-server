--
-- PostgreSQL database dump
--

copy public.projets (id, date_debut, date_fin, description, p_reference, pupdated_date, title, totalsp_commitment, totalsp_completed, totalstorypointsinitiallycounts) FROM '/home/meher/Desktop/datasqlkpiagile/projets.csv' CSV QUOTE '\"' ESCAPE '''';""

copy public.sprints (id, more_sp, s_reference, sdate_debut, sdate_fin, description, title, supdated_date, work_commitment, work_completed, projet_id) FROM '/home/meher/Desktop/datasqlkpiagile/sprints.csv' CSV QUOTE '\"' ESCAPE '''';""

USE postgres;
copy public.story (id, plus_sp, priority, sp_completed, st_reference, description, title, story_point, stupdated_date, sprint_id) FROM '/home/meher/Desktop/datasqlkpiagile/story.csv' CSV QUOTE '\"' ESCAPE '''';""

copy public.tasks (id, bugs, estimation, status, t_reference, tdate_debut, tdate_fin, description, title, tsupdated_date, type_task, story_id) FROM '/home/meher/Desktop/datasqlkpiagile/task.csv' CSV QUOTE '\"' ESCAPE '''';""

copy public.users (id, email, password, username) FROM '/home/meher/Desktop/datasqlkpiagile/user.csv' CSV QUOTE '\"' ESCAPE '''';""

copy public.users_roles (user_id, role_id) FROM '/home/meher/Desktop/datasqlkpiagile/userrole.csv' CSV QUOTE '\"' ESCAPE '''';""

copy public.workedl_sprints (sprint_id, workedl_array) FROM '/home/meher/Desktop/datasqlkpiagile/workedl_sprints' CSV QUOTE '\"' ESCAPE '''';""

copy public.days_sprints (sprint_id, days_array) FROM '/home/meher/Desktop/datasqlkpiagile/days_sprints' CSV QUOTE '\"' ESCAPE '''';""

copy public.ideall_sprints (sprint_id, il_array) FROM '/home/meher/Desktop/datasqlkpiagile/ideall_sprints' CSV QUOTE '\"' ESCAPE '''';""

copy public.projet_more_sp (projet_id, project_more_sp) FROM '/home/meher/Desktop/datasqlkpiagile/projet_more_sp' CSV QUOTE '\"' ESCAPE '''';""

copy public.projet_percentage_spc (projet_id, percentage_spc) FROM '/home/meher/Desktop/datasqlkpiagile/projet_percentage_spc' CSV QUOTE '\"' ESCAPE '''';""

copy public.projet_sp_commitment (projet_id, project_sp_commitment) FROM '/home/meher/Desktop/datasqlkpiagile/projet_sp_commitment' CSV QUOTE '\"' ESCAPE '''';""

copy public.projet_sp_completed (projet_id, project_sp_completed) FROM '/home/meher/Desktop/datasqlkpiagile/projet_sp_completed' CSV QUOTE '\"' ESCAPE '''';""