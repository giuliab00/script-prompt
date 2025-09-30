def get_context_description(task_name: str, age: str, sex: str):
    """Return the full context string for a given task, age, and gender."""

    # All task contexts
    CONTEXTS = {
        "pre": f"A robot and a {age} year old {sex} user are interacting in a quiet room. On the table in front of them there are several objects. The robot has already introduced itself and drawn attention to the objects on the table.",

        "beads": f"A robot and a {age} year old {sex} user are interacting in a quiet room. The robot and the user are interacting after the robot invited the user to divide tiny coloured beads by colour using tweezers and place them, sorted by colour, into the clear organizer beside them - within 5 minutes. The user is free to complete the task or refuse it.",

        "post-beads": f"A robot and a {age} year old {sex} user are interacting in a quiet room. The robot invited the user to use tweezers to sort beads by color and place them into a clear organizer, with each color in its own section - within 5 minutes. The user was free to complete the task or refuse it. Now the time is up and the robot asked the user how they are feeling.",

        "memory": f"A robot and a {age} year old {sex} user are interacting in a quiet room. The robot and the user are interacting after the robot invites the user to complete a card-matching memory game by themselves - within 5 minutes. The user is free to complete the task or refuse it.",

        "post-memory": f"A robot and a {age} year old {sex} user are interacting in a quiet room. The robot invited the user to complete a card-matching memory game within 5 minutes. The user was free to complete the task or refuse it. Now the time is up and the robot asks the user how they are feeling.",

        "mandala": f"A robot and a {age} year old {sex} user are interacting in a quiet room. The robot and the user are interacting after the robot invites the user to colour a mandala drawing using a set of colour markers, copying the nearby coloured mandala - within 5 minutes. The user is free to complete the task or refuse it.",

        "post-mandala": f"A robot and a {age} year old {sex} user are interacting in a quiet room. The robot invited the user to colour a mandala drawing using a set of colour markers, copying the nearby coloured mandala - within 5 minutes. The user was free to complete the task or refuse it. Now the time is up and the robot asks the user how they are feeling.",

        "jacks": f"A robot and a {age} year old {sex} user are interacting in a quiet room. The robot and the user are interacting after the robot invites the user to play the Jacks game - a game in which the user must collect all the small, star-shaped metal pieces with one hand while bouncing a red ball - within 5 minutes. The user is free to complete the task or refuse it.",

        "post-jacks": f"A robot and a {age} year old {sex} user are interacting in a quiet room. The robot invited the user to play the Jacks game - a game in which the user must collect all the small, star-shaped metal pieces with one hand while bouncing a red ball - within 5 minutes. The user was free to complete the task or refuse it. Now the time is up and the robot asks the user how they are feeling.",

        "jump": f"A robot and a {age} year old {sex} user are interacting in a quiet room. The robot and the user are interacting after the robot invites the user to do a 3x15 jumping jacks set using a stretching band — within 5 minutes. The user is free to complete the task or refuse it.",

        "post-jump": f"A robot and a {age} year old {sex} user are interacting in a quiet room. The robot invited the user to do a 3x15 jumping jacks set using a stretching band — within 5 minutes. The user was free to complete the task or refuse it. Now the time is up and the robot asks the user how they are feeling."
    }

    return CONTEXTS.get(task_name)  # Fallback


def define_gt_description(gt_key):
    """Return the full textual grounding for a given predicate key."""
    MAPPING = {
        'hard': 'the user finds the task too hard; for instance it is too challenging, difficult or frustrating',
        'easy': 'the user finds the task too easy; for instance it is not sufficiently challenging',
        'bored': 'the user feels bored; for instance, the user perceives the task as repetitive, or the task is not interesting or engaging enough',
        'tired': 'the user feels physically or mentally tired or both; for instance, they have been working for too long or they are not well rested',
        'hungry': 'the user is hungry; for instance they would like to eat something',
        'succeed': 'the user exhibits a competitive attitude while performing the task; for instance, they are highly determined to succeed or to outperform others',
        'fussy': 'the user has a perfectionist or fussy attitude about everything; for instance they pay great attention to details or are very particular about how things should be done',
        'curious': 'the user is curious; for instance they are interested in the task outcome or in exploring different things or activities',
        'uncomfortable': 'the user feels uncomfortable; for instance the discomfort could be caused by the environment, the presence of the tutor, or an inner feeling of pain or unease',
        'frustrated': 'the user feels frustrated; for instance, they perceive a feeling of dissatisfaction and discomfort in trying to achieve the desired goal of the task',
        'satisfied': 'the user feels satisfied; for instance, they feel pleasure from achieving a goal or satisfying a need',
        'confused': 'the user feels confused; for instance, they find the information they received ambiguous or contradictory, and they find it difficult to understand what is happening or to continue with the task'
    }
    return MAPPING.get(gt_key, f"the user shows signs of '{gt_key}'")


def build_system_grounding_prompt(task, age, sex, gt_key):

    context = get_context_description(task_name=task, age=age, sex=sex)
    
    gt_text = define_gt_description(gt_key)

    prompt = (
        f"You are provided with a verbal interaction about the following situation:\n\n"
        f"{context}\n\n"
        f"Focus on the user's feelings and attitudes.\n\n"
        f"Do you think that {gt_text}?\n"
        f"Answer only with 'yes' or 'no'."
    )
    
    return prompt

if __name__ == "__main__":
    build_system_grounding_prompt("beads", 24, "female", "bored")