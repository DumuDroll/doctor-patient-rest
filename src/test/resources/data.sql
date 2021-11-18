INSERT INTO public.doctors(id, name, experience)
VALUES (1, 'Dmytro', '5 years');
INSERT INTO doctors(id, name, experience)
VALUES (2, 'Petro', '15 years');
INSERT INTO doctors(id, name, experience)
VALUES (3, 'Pavlo', '3 years');
INSERT INTO drugs(id, name)
VALUES (1, 'Aspirin');
INSERT INTO drugs(id, name)
VALUES (2, 'Paracetamol');
INSERT INTO drugs(id, name)
VALUES (3, 'Nurofen');
INSERT INTO drugs(id, name)
VALUES (4, 'Gepanex');
INSERT INTO patients(id, first_name, last_name, doctor_id)
VALUES (1, 'Dmytro', 'Korkhovyi', 1);
INSERT INTO patients(id, first_name, last_name, doctor_id)
VALUES (2, 'Gregor', 'Botka', 1);
INSERT INTO patients(id, first_name, last_name, doctor_id)
VALUES (3, 'Igor', 'Helm', 1);
INSERT INTO patients(id, first_name, last_name, doctor_id)
VALUES (4, 'Pavel', 'Russo', 2);
INSERT INTO patients(id, first_name, last_name, doctor_id)
VALUES (5, 'Kirk', 'Dirk', 2);
INSERT INTO patients(id, first_name, last_name, doctor_id)
VALUES (6, 'Momba', 'Boonie', 3);
INSERT INTO full_info(id, birth_date, email, phone_number, patient_id)
VALUES (1, '26.05.1999', 'd.korkhovyi@infostroy.com.ua', '+380981001950', 1);
INSERT INTO full_info(id, birth_date, email, phone_number, patient_id)
VALUES (2, '04.09.1979', 'g.botka@infostroy.com.ua', '+244224422441', 2);
INSERT INTO full_info(id, birth_date, email, phone_number, patient_id)
VALUES (3, '13.12.2000', 'i.helm@infostroy.com.ua', '+666666666667', 3);
INSERT INTO full_info(id, birth_date, email, phone_number, patient_id)
VALUES (4, '05.03.1993', 'p.russo@infostroy.com.ua', '+544534512311', 4);
INSERT INTO full_info(id, birth_date, email, phone_number, patient_id)
VALUES (5, '26.09.1998', 'k.dirk@infostroy.com.ua', '+234234232233', 5);
INSERT INTO full_info(id, birth_date, email, phone_number, patient_id)
VALUES (6, '11.11.1992', 'm.boonie@infostroy.com.ua', '+76563657567', 6);
INSERT INTO patients_drugs(patient_id, drug_id)
VALUES (1, 1);
INSERT INTO patients_drugs(patient_id, drug_id)
VALUES (1, 2);
INSERT INTO patients_drugs(patient_id, drug_id)
VALUES (2, 2);
INSERT INTO patients_drugs(patient_id, drug_id)
VALUES (2, 3);
INSERT INTO patients_drugs(patient_id, drug_id)
VALUES (3, 1);
INSERT INTO patients_drugs(patient_id, drug_id)
VALUES (3, 2);
INSERT INTO patients_drugs(patient_id, drug_id)
VALUES (3, 3);
INSERT INTO patients_drugs(patient_id, drug_id)
VALUES (4, 1);
INSERT INTO patients_drugs(patient_id, drug_id)
VALUES (5, 4);