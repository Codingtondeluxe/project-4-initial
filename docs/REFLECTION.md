# Reflection - AI-Assisted Development Experience

**Project:** Project 4 - Generic Container (Bag)  
**Date:** April 8, 2026

---

## Overview

For this project I used GitHub Copilot to generate the `Bag<E>` implementation and a full JUnit 5 test suite. The experience showed me both the strengths and the limitations of AI-assisted coding.

---

## What Worked Well

**Speed of scaffolding.** The initial `Bag<E>` class came out structurally correct on the first prompt. All five interface methods were delegated to the backing `ArrayList`, and `iterator()` was wired up properly by returning `items.iterator()`. There were no compilation errors or structural issues to fix.

**Specificity matters.** When I was explicit in the prompt - naming the required interface, specifying `ArrayList` as the mandatory backing structure, and calling out that `iterator()` must be implemented - the output matched the requirements exactly. Vague prompts produce vague results; specific prompts produce specific code.

**Test coverage.** Asking the AI to generate tests against a numbered list of scenarios worked well. It turned each scenario into a clearly named test method and used the right JUnit 5 assertions without needing extra guidance.

---

## What Required Refinement

**Null-handling was wrong for this project.** The first version of `Bag.add()` silently accepted null because `ArrayList` allows it. I had to follow up and ask for an `IllegalArgumentException` on null input. This is a good example of why reviewing AI output matters. It did something technically valid but not suitable for this use case.

**Test class visibility.** The AI declared `BagTest` as `public class`. JUnit 5 does not require public test classes, and the IDE flagged it as an unnecessary modifier. A small fix, but it is the kind of detail an AI does not always get right without being told.

---

## Lessons Learned

1. **You still have to review the output.** The AI generated most of the code correctly but got the null-handling wrong. It silently allowed nulls because that is what `ArrayList` does by default. I caught that by reading the code and knowing what behavior I wanted.
2. **The AI does not know your specific requirements unless you tell it.** The null rejection and the `ArrayList` backing only ended up correct because I included them in the prompt. If I had just said "make a Bag class" the result would have been missing key details from the rubric.
3. **Understanding the code matters even if you did not write it.** The AI generated it but I had to verify it made sense.

---

## Conclusion

AI assistance sped up the coding portion of this project. The time saved on boilerplate let me focus on design decisions like the null policy and iterator contract, and on trying to understand what the generated code was actually doing. Using AI for speed while applying my own judgment for correctness was what made the workflow effective.
