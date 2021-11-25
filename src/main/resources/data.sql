INSERT INTO doctors(id, name, experience)
VALUES (1, 'testName1', 'testExp1');
INSERT INTO doctors(id, name, experience)
VALUES (2, 'testName2', 'testExp2');
INSERT INTO doctors(id, name, experience)
VALUES (3, 'testName3', 'testExp3');
INSERT INTO doctors(id, name, experience)
VALUES (4, 'testName1', 'testExp1');
INSERT INTO doctors(id, name, experience)
VALUES (5, 'testName2', 'testExp2');
INSERT INTO doctors(id, name, experience)
VALUES (6, 'testName3', 'testExp3');
INSERT INTO doctors(id, name, experience)
VALUES (7, 'testName1', 'testExp1');
INSERT INTO doctors(id, name, experience)
VALUES (8, 'testName2', 'testExp2');
INSERT INTO doctors(id, name, experience)
VALUES (9, 'testName3', 'testExp3');
INSERT INTO doctors(id, name, experience)
VALUES (10, 'testName1', 'testExp1');
INSERT INTO doctors(id, name, experience)
VALUES (12, 'testName2', 'testExp2');
INSERT INTO doctors(id, name, experience)
VALUES (13, 'testName3', 'testExp3');
INSERT INTO doctors(id, name, experience)
VALUES (14, 'testName1', 'testExp1');
INSERT INTO doctors(id, name, experience)
VALUES (15, 'testName2', 'testExp2');
INSERT INTO doctors(id, name, experience)
VALUES (16, 'testName3', 'testExp3');
INSERT INTO drugs(id, name)
VALUES (1, 'drug1');
INSERT INTO drugs(id, name)
VALUES (2, 'drug2');
INSERT INTO drugs(id, name)
VALUES (3, 'drug3');
INSERT INTO patients(id, first_name, last_name, doctor_id)
VALUES (1, 'testfName1', 'testlName1', 1);
INSERT INTO patients(id, first_name, last_name, doctor_id)
VALUES (2, 'testfName2', 'testlName2', 2);
INSERT INTO patients(id, first_name, last_name, doctor_id)
VALUES (3, 'testfName3', 'testlName3', 3);
INSERT INTO full_info(id, birth_date, email, phone_number, patient_id)
VALUES (1, 'testDate1', 'testEmail1', 'testNumber1', 1);
INSERT INTO full_info(id, birth_date, email, phone_number, patient_id)
VALUES (2, 'testDate2', 'testEmail2', 'testNumber2', 2);
INSERT INTO full_info(id, birth_date, email, phone_number, patient_id)
VALUES (3, 'testDate3', 'testEmail3', 'testNumber3', 3);
INSERT INTO patients_drugs(patient_id, drug_id)
VALUES (1, 1);
INSERT INTO patients_drugs(patient_id, drug_id)
VALUES (2, 2);
INSERT INTO patients_drugs(patient_id, drug_id)
VALUES (3, 3);