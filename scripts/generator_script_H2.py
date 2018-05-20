from datetime import datetime
from random import choice, randint

ROLES = ['ADMIN', 'USER', 'VERIFIED']
NAMES = ['Mark', 'Zhenya', 'Abby', 'James', 'Lucas', 'Ethan', 'Liam', 'Noah', 'Daniel', 'Oliver', 'Michael', 'Benjamin']
LASTNAMES = ['Smith', 'Jones', 'Brown', 'Johnson', 'Wilson', 'Miller', 'Green', 'Scott', 'Wood', 'Adams', 'Turner']
PATRONYMICS = ['Smith', 'Jones', 'Brown', 'Johnson', 'Wilson', 'Miller', 'Green', 'Scott', 'Wood', 'Adams', 'Turner']
SUBJECTS = ['certificate', 'math', 'biology', 'geography', 'english', 'russian', 'history']

UNIVERSITIES_COUNT = 1
FACULTIES_COUNT = 6
GROUPS_COUNT = 20
USERS_COUNT = 1000

def get_insert_template(template=""):

    return '{0} {1}'.format('INSERT INTO', template)


def get_id_or_null(first=0, last=0):
    group_id = randint(first, last)
    if group_id == 0:
        group_id = 'null'
    return group_id


def generate_query(template, func, count):
    return '{0} {1};'.format(template, "".join(func(i + 1) for i in range(count))[0: -2])


def generate_user_informations(count=1):
    def generate_user_information(index=1):
        return "({0},'{1}','{2}','{3}'), ".format(index, "email_" + str(index) + "@gmail.com", 'nickname_' + str(index),
                                                  'password_' + str(index))

    template = get_insert_template("user_information (id, email, nickname, password) VALUES")
    return generate_query(template, generate_user_information, count)


def generate_roles(count=1):
    def generate_role(index=1):
        return "({0}, '{1}'), ".format(index, ROLES[index - 1])

    template = get_insert_template("roles VALUES")
    return generate_query(template, generate_role, count)


def generate_subjects(count=1):
    def generate_subject(index=1):
        return "({0}, '{1}'), ".format(index, SUBJECTS[index - 1])

    template = get_insert_template("subjects VALUES")
    return generate_query(template, generate_subject, count)


def generate_user_subjects(count=1):
    def generate_user_subject(index=1):
        subj1 = 1
        while (subj1 == 1): subj1 = randint(1, len(SUBJECTS) - 1)
        subj2 = subj1
        while (subj2 == subj1): subj2 = randint(1, len(SUBJECTS) - 1)
        return "({0},{1}, {4}), ({0},{2}, {5}), ({0},{3},{6}), ".format(index, 1, subj1 + 1, subj2 + 1,
                                                                        randint(10, 100), randint(10, 100),
                                                                        randint(10, 100))

    template = get_insert_template("users_subjects (users_id, subjects_id, mark) VALUES")
    return generate_query(template, generate_user_subject, count)


def generate_group_subjects(count=1):
    def generate_group_subject(index=1):
        subj1 = 1
        while (subj1 == 1): subj1 = randint(1, len(SUBJECTS) - 1)
        subj2 = subj1
        while (subj2 == subj1): subj2 = randint(1, len(SUBJECTS) - 1)
        return "({1},{0}), ({2},{0}), ({3},{0}), ".format(index, 1, subj1 + 1, subj2 + 1)

    template = get_insert_template("groups_subjects(SUBJECTS_ID, GROUPS_ID)  VALUES")
    return generate_query(template, generate_group_subject, count)


def generate_universities(count=1):
    def generate_university(index=1):
        return "({0}, '{1}'), ".format(index, 'University#' + str(index))

    TEMPLATE = get_insert_template("UNIVERSITIES (id, NAME) VALUES")
    return generate_query(TEMPLATE, generate_university, count)


def generate_faculties(count=1):
    def generate_faculty(index=1):
        return "({3}, '{0}','{1}',{2}), ".format('Faculty#' + str(index), 'Faculty#' + str(index),
                                                 get_id_or_null(1, UNIVERSITIES_COUNT), index)

    TEMPLATE = get_insert_template('FACULTIES(id, INFORMATION, NAME, UNIVERSITIES_ID) VALUES')
    return generate_query(TEMPLATE, generate_faculty, count)


def generate_groups(count=1):
    def generate_group(index=1):
        return "({0},{1},{2},{3},'{4}','{5}',{6},'{7}','{8}',{9}), ".format(str(index), 0, 0, 0,
                                                                            "group#" + str(index),
                                                                            str(datetime.now().date()),
                                                                            str(randint(3, 10)),
                                                                            str(datetime.now().date()),
                                                                            'programmer',
                                                                            str(get_id_or_null(1, FACULTIES_COUNT)))

    TEMPLATE = get_insert_template('groups(id, ENROLL_MARK, COUNT_OF_USERS, COUNT_OF_ALL_USERS, INFORMATION, '
                                   'VALID_TILL,COUNT,ISSUE_DATE, QUALIFY, FACULTIES_ID) VALUES')
    return generate_query(TEMPLATE, generate_group, count)


def generate_users(count=1):
    def generate_user(index=1):
        return "({4},'{0}','{1}','{2}',{3}), ".format(choice(NAMES), choice(LASTNAMES), choice(PATRONYMICS),
                                                      str(get_id_or_null(1, GROUPS_COUNT)), index)

    TEMPLATE = get_insert_template('users(id, name, lastname, patronymic, groups_id) VALUES')
    return generate_query(TEMPLATE, generate_user, count)


def generate_user_roles(count=1):
    def generate_role(index=1):
        query = "({0},{1}), ".format(0, index)
        if randint(1, 4) > 1:
            query += "({0},{1}), ".format(2, index)
        return query

    template = get_insert_template('user_information_roles (ROLES, USER_INFORMATION_ID) VALUES')
    return generate_query(template, generate_role, count)


if __name__ == '__main__':

    print(generate_subjects(len(SUBJECTS)))
    print(generate_universities(UNIVERSITIES_COUNT))
    print(generate_faculties(FACULTIES_COUNT))
    print(generate_groups(GROUPS_COUNT))
    print(generate_group_subjects(GROUPS_COUNT))
    print(generate_users(USERS_COUNT))
    print(generate_user_informations(USERS_COUNT))
    print(generate_user_subjects(USERS_COUNT))
    print(generate_user_roles(USERS_COUNT))
