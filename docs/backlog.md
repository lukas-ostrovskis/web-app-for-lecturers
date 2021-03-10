# Backlog

## Function requirements:
### Must Have:
1. As a lecturer, I want to be able to create rooms and share them with the students (via a link or some other way) so that students have a platform where they can ask questions.
1. As a lecturer, I want to be authenticated so that only my role can create rooms.
1. As a lecturer, I want to see questions that students ask in the room that I've created so that I can answer them.
1. As a moderator, I want to be able to hide/remove questions if they violate any kind of a rule.
1. As a student, I want to be able to upvote questions from other students so that the lecturer sees the most relevant questions which need to be answered.
1. As a lecturer, I want to see top N questions with the highest number of upvotes that students have asked in the room that I've created so that I can easily answer the most popular questions.
1. As a student, I want to be able to mark my own questions as “answered”
1. As a student, I want to be able to ask questions in the room.
1. As a student, I want to be able to join the room that was shared with me with an ID (randomly/manually generated) or a link.

### Should Have:
1. As a student, I want to be able to downvote questions from other students.
1. As a moderator, I want to be able to ban certain IPs/users.
1. As a lecturer, I want to be able to create polls so that I can ask students multiple choice questions
1. As a lecturer, I want to be able to publish the answer distribution of my polls so that both me and the students can see how the majority understood the question.

### Could Have:
1. As a student, I want to be able to reply to other students' questions.
1. As a lecturer, I want to be able to archive a room.
1. As a lecturer, I want to be able to choose how many top questions to see.
1. As a lecture/student I want to be able to see downvoted questions visualized differently.
1. As a student I want to be able to see the questions in a sorted way (depending on upvotes)

### Won’t Have:
1. Emojis support?
1. SSO authentication.
1. As a student. I want to be able to re-edit my question.
1. Cross-platform.



## Non-functional requirements:
1. Java
1. Spring
1. Questions with their number of upvotes are stored in an H2 database.
1. Every created room should be able to handle up to 500 students.
1. The server should be able to handle at least 15 rooms (30% of all students)
1. Every 5 seconds the list of upvoted questions should update - server polls new questions
1. You can only post a question every 10 seconds.
1. The software should run on Windows 10.
