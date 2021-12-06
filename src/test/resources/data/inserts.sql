INSERT INTO doctors(id, name, experience)
VALUES (1, 'testName1', 'testExp1');
INSERT INTO doctors(id, name, experience)
VALUES (2, 'testName2', 'testExp2');
INSERT INTO doctors(id, name, experience)
VALUES (3, 'testName3', 'testExp3');
INSERT INTO drugs(id, name)
VALUES (1, 'drug1');
INSERT INTO drugs(id, name)
VALUES (2, 'drug2');
INSERT INTO drugs(id, name)
VALUES (3, 'drug3');
INSERT INTO full_info(id, email, phone_number)
VALUES (1, 'testEmail1', 'testNumber1');
INSERT INTO full_info(id, email, phone_number)
VALUES (2,  'testEmail2', 'testNumber2');
INSERT INTO full_info(id, email, phone_number)
VALUES (3, 'testEmail3', 'testNumber3');
INSERT INTO patients(id, first_name, last_name, doctor_id, full_info_id)
VALUES (1, 'testfName1', 'testlName1', 1, 1);
INSERT INTO patients(id, first_name, last_name, doctor_id, full_info_id)
VALUES (2, 'testfName2', 'testlName2', 2, 2);
INSERT INTO patients(id, first_name, last_name, doctor_id, full_info_id)
VALUES (3, 'testfName3', 'testlName3', 3, 3);
INSERT INTO patients_drugs(patient_id, drug_id)
VALUES (1, 1);
INSERT INTO patients_drugs(patient_id, drug_id)
VALUES (2, 2);
INSERT INTO patients_drugs(patient_id, drug_id)
VALUES (3, 3);