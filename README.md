# Human–Robot Interaction Prompt Framework

This repository contains three core files that define the interaction logic, task prompts, and grounding checks used in our study. The materials were designed to support experiments in which a social robot engages users with short, structured tasks and conversational prompts.

## Files Overview

### 1. `speaking_prompt.py`
- **Purpose:** Defines the **task-specific system prompts** that guide the robot’s spoken interactions with users.  
- **Content:**  
  - A set of **task templates** (e.g., beads sorting, memory game, mandala coloring, jacks, jumping jacks).  
  - Each template specifies the available objects, timing rules, and conversation constraints.  
  - The function `build_system_prompt(...)` dynamically generates a complete robot prompt, conditioned on:
    - The current task
    - User’s name and demographic information
    - Language setting (Italian/English)
    - User’s last utterance  

---

### 2. `script.kt`
- **Purpose:** Implements the **script manager** in Kotlin, used for sequencing and presenting task instructions.  
- **Content:**  
  - Provides introduction, start, stop, and farewell scripts.  
  - Randomly assigns a task and retrieves the corresponding instruction block (English or Italian).  
  - Supports gender-sensitive forms in Italian (e.g., *pronta/pronto*, *venuta/venuto*).  
  - Contains pre-task and task-specific instructions for beads, mandala, memory game, jacks, and jumping jacks.  
---

### 3. `grounding_prompt.py`
- **Purpose:** Defines **grounding prompts** for post-task evaluation of user attitudes and mental states.  
- **Content:**  
  - Contextual descriptions of each task (pre, task, and post-task situations).  
  - A mapping of user state labels (e.g., *bored*, *frustrated*, *satisfied*) to natural language descriptions.  
  - The function `build_system_grounding_prompt(...)` generates a yes/no diagnostic question about whether a user exhibits a given state in the interaction.  
