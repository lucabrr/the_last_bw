import os
import subprocess
import time

modified = False
new_str = ''

def import_renamer(to_replace, file_path, input_str):
    with open(file_path, 'r') as file:
        content = file.read()
    new_content = content.replace(to_replace, input_str)
    modified = True
    with open(file_path, 'w') as file:
        file.write(new_content)

while True:
    print("1 >> Rinomina da com.epicode.Spring")
    print('2 >> Rinomina da package già modificato')
    user_input = input('Seleziona 1 oppure 2 >> ')

    if (user_input) == '2':
        user_pkg_to_replace = input('Bene, ora inserisci il nome del package da cambiare >> ')

    user_pkg_name = input('Infine, inserisci il nome del tuo nuovo package >> ')
    
    for root, dirs, files in os.walk(os.getcwd()):
        for name in files:
            if '.java' in name:
                if user_input == '1':
                    import_renamer('com.epicode.Spring', os.path.join(root, name), user_pkg_name)
                else:
                    import_renamer(user_pkg_to_replace, os.path.join(root, name), user_pkg_name)
    print("Fatto! Controlla di aver inserito correttamente il nome del package.")
    user_resp = input("Uscire dal programma? > Y/N >> ")
    if user_resp == 'y' or user_resp == 'Y':
        break