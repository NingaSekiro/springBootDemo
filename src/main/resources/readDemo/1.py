import openpyxl

def check_personal_info(file_path):
    workbook = openpyxl.load_workbook(file_path)
    core_props = workbook.properties

    personal_info = ["name", "company"]  # Add more personal attributes if necessary
    for info in personal_info:
        if getattr(core_props, info):
            print(f"File contains personal info in property {info}: {getattr(core_props, info)}")

check_personal_info('1.xlsx')
