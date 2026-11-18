def areInClass(groups, classList):
    var = True
    for group in groups:
        for pupil in group:
            if pupil not in classList:
                return False
    return True