# The maths behind CGPAAmortizedAnalysis

The app answers one question: _given my semesters so far, what is the lowest GPA I can score next semester and still have a CGPA of at least 9.0 afterwards?_ It answers it for every plausible next-semester credit load from 16 to 27.

This document derives the formula the code uses, connects it to amortized analysis (the idea behind the app's name), and works through examples. Line numbers refer to `HEAD` (`822bb5a`), with paths relative to
`app/src/main/java/abhinav/hackdev/co/amortizedanalysis/`.

## Contents

1. [Inputs and grading model](#1-inputs-and-grading-model)
2. [CGPA](#2-cgpa)
3. [Minimum GPA for next semester](#3-minimum-gpa-for-next-semester)
4. [Why "amortized"?](#4-why-amortized)
5. [Properties worth knowing](#5-properties-worth-knowing)
6. [Worked examples](#6-worked-examples)
7. [Numerics and edge cases](#7-numerics-and-edge-cases)
8. [Natural extension: spreading over k semesters (not implemented)](#8-natural-extension-spreading-over-k-semesters-not-implemented)

---

## 1. Inputs and grading model

The app assumes a **10-point, credit-weighted** grading scheme with **eight semesters**, as used at many Indian engineering universities:

| Input                      | Symbol  | Constraint in code                            | Where                                                 |
| -------------------------- | ------- | --------------------------------------------- | ----------------------------------------------------- |
| GPA of semester $i$        | $g_i$   | non-empty and $0 \le g_i \le 10$              | `View/Fragments/GPAFragment.java` L68                 |
| Credits of semester $i$    | $c_i$   | non-empty whole number (`inputType="number"`) | `GPAFragment.java` L68, `res/layout/fragment_gpa.xml` |
| Number of semesters        | up to 8 | one pager page per semester                   | `Controller/CustomAdapter.java` L27–29                |
| Next-semester credit loads | $n$     | $n \in \lbrace 16, 17, \dots, 27\rbrace$      | `View/Fragments/AlertDialogFragment.java` L80–82      |
| Target CGPA                | $T$     | hard-coded **9**                              | `Model/Entities/CalculateAmortizedGPA.java` L30       |

Each submitted semester becomes a `GPAData(semGPA, semCreds, semVal)` (`Model/Entities/GPAData.java`) appended to `MainActivity.gpaDataArrayList` (`View/Activities/MainActivity.java` L124).

Throughout, let

$$
C = \sum_{i=1}^{k} c_i \qquad\text{and}\qquad S = \sum_{i=1}^{k} g_i\,c_i
$$

be the credits and grade points earned over the $k$ semesters entered so far.

## 2. CGPA

**Code:** `Model/Entities/CalculateCGPA.getCGPA()`, L19–29.

CGPA is the credit-weighted mean of semester GPAs:

$$
\mathrm{CGPA} = \frac{S}{C} = \frac{\sum_i g_i\,c_i}{\sum_i c_i}
$$

The loop accumulates $S$ into `finalCGPA` and $C$ into `totalCredits`, then divides (L20–25). After each submission, `MainActivity.printArrayList()` shows the result with a verdict (L158–162):

| Condition           | Message                             |
| ------------------- | ----------------------------------- |
| $\mathrm{CGPA} > 9$ | `Go and Explore the world! CGPA: …` |
| otherwise           | `Start Studying! CGPA: …`           |

## 3. Minimum GPA for next semester

**Code:** `Model/Entities/CalculateAmortizedGPA.getMinPredictedGPA(int nextCreds)`, L28–33.

After a next semester of $n$ credits at GPA $g$, the new CGPA is

$$
\mathrm{CGPA}' = \frac{S + g\,n}{C + n}
$$

Requiring $\mathrm{CGPA}' \ge T$ and solving for $g$ gives the minimum GPA:

$$
g^{*}(n) = \frac{T\,(C + n) - S}{n}
$$

which is exactly what the code computes with $T = 9$, using $S = C \cdot \mathrm{CGPA}$:

```java
estimatedGPA = 9*(getCurrentTotalCreds() + nextCreds) - (getCurrentTotalCreds()*currentCGPA);   // L30
estimatedGPA = estimatedGPA/nextCreds;                                                         // L31
```

Substituting $S = C \cdot \mathrm{CGPA}$ and simplifying gives a form that is easier to reason about:

$$
g^{*}(n) = T + \frac{C\,(T - \mathrm{CGPA})}{n}
$$

`AlertDialogFragment.setData()` evaluates $g^{_}(n)$ for $n = 16 \dots 27$ (L80–82). `AmortizedAdapter` renders one row per $n$, printing **"Above 10"** when $g^{_}(n) > 10$ (`Controller/AmortizedAdapter.java` L35–39), because no GPA on a 10-point scale reaches the target that semester. The dialog title is _"Minimum GPA required to maintain CGPA 9"_ (`res/layout/fragment_alert_amortized.xml`).

## 4. Why "amortized"?

In algorithm analysis, _amortized_ cost spreads the cost of an expensive history over a sequence of operations. The app's formula is the same bookkeeping applied to grade points. The code computes the closed form from §3 directly; the two views below explain the name and give the same answer.

### Aggregate view

To finish next semester at CGPA $T$, you need $T\,(C + n)$ grade points in total. You have already "paid" $S$. The remaining bill is spread evenly over the $n$ new credits:

$$
g^{*}(n) = \frac{\overbrace{T\,(C + n)}^{\text{total required}} - \overbrace{S}^{\text{already paid}}}{n}
$$

### Potential (banker's) view

Define a **potential**: the grade-point surplus or deficit relative to the target:

$$
\Phi = S - T\,C = C\,(\mathrm{CGPA} - T)
$$

- $\Phi > 0$: you have banked credit and can afford a next semester below $T$.
- $\Phi < 0$: you are in debt, and next semester must repay it.

Next semester, every credit "costs" $T$ and you "earn" $g$ per credit. Staying solvent means

$$
n\,g + \Phi \;\ge\; n\,T \quad\Longleftrightarrow\quad g \;\ge\; T - \frac{\Phi}{n}
$$

Since $-\Phi / n = C\,(T - \mathrm{CGPA})/n$, this is identical to §3. The surplus or debt $\Phi$ is _amortized_ over the $n$ credits of the next semester.

## 5. Properties worth knowing

All of these follow from $g^{*}(n) = T + C\,(T - \mathrm{CGPA})/n$.

**Heavier semesters pull you towards $T$.** As $n$ grows, $g^{*}(n) \to T$. If you are below target, a heavier load *lowers* the GPA you need. If you are above target, it *raises* it, because your banked surplus is spread over more credits.

**When is the target reachable in one semester?** On a 10-point scale you need $g^{*}(n) \le 10$, which rearranges to

$$
n \;\ge\; \frac{C\,(T - \mathrm{CGPA})}{10 - T} \;=\; C\,(T - \mathrm{CGPA}) \quad \text{for } T = 9
$$

If even $n = 27$ fails this test, every row in the dialog reads "Above 10".

**No lower clamp.** If you are far above target, $g^{*}(n)$ can drop below 0. The app prints that negative number as-is; it only clamps the top, with "Above 10". A negative value means you would stay above 9 even with a GPA of 0.

## 6. Worked examples

### A. The sample data shipped in the code

`View/Fragments/AlertFragment.java` (an activity, despite its name) hard-codes four semesters (L45–49):

| Semester  | GPA $g_i$ | Credits $c_i$ | $g_i\,c_i$  |
| --------- | --------- | ------------- | ----------- |
| 1         | 9.4       | 24            | 225.6       |
| 2         | 9.3       | 26            | 241.8       |
| 3         | 9.2       | 24            | 220.8       |
| 4         | 9.0       | 24            | 216.0       |
| **Total** |           | $C = 98$      | $S = 904.2$ |

$\mathrm{CGPA} = 904.2 / 98 \approx 9.2265$, so $\Phi = 98 \times 0.2265 \approx 22.2$ banked grade points.

| $n$        | 16   | 18   | 20   | 22   | 24   | 26   | 27   |
| ---------- | ---- | ---- | ---- | ---- | ---- | ---- | ---- |
| $g^{*}(n)$ | 7.61 | 7.77 | 7.89 | 7.99 | 8.08 | 8.15 | 8.18 |

You can score as low as about 7.6 on a 16-credit semester and still hold 9.0. The required GPA creeps up with heavier loads, as §5 predicts.

### B. Below target (illustrative inputs)

Two semesters: 8.2 over 23 credits and 8.6 over 25 credits. So $C = 48$, $S = 403.6$ and $\mathrm{CGPA} \approx 8.408$.

| $n$        | 16       | 20       | 24       | 27       |
| ---------- | -------- | -------- | -------- | -------- |
| $g^{*}(n)$ | 10.77    | 10.42    | 10.18    | 10.05    |
| App shows  | Above 10 | Above 10 | Above 10 | Above 10 |

The reachability bound from §5 gives $n \ge 48 \times 0.592 \approx 28.4$ credits, which is more than the largest load in the table. So no single semester can bring this student back to 9.0. That is the motivation for §8.

> Values computed with 32-bit floats to mirror the Java code, then rounded to two decimals. The app itself prints the raw `float` (e.g. `7.612503`).

## 7. Numerics and edge cases

- **Precision.** Everything is Java `float` (32-bit). `String.valueOf(float)` is used for display, so values appear with up to seven or eight significant digits.
- **Zero credits.** Credits are only checked for being non-empty. If every semester entered so far has 0 credits, $C = 0$ and `getCGPA()` returns `NaN` (0/0).
- **Re-submitting a semester.** `MainActivity.updateChartFromGPA()` _appends_ a new `GPAData` on every submit (L124). Pressing "Add GPA" twice on the same page counts that semester twice in $C$ and $S$.
- **Order of entry.** `GPAList.addToList()` inserts at index `position - 1` in a static `ArrayList` (`Model/Entities/GPAList.java` L30–33). Submitting a later semester before the earlier ones (for example, swiping to SEM 3 first) throws an uncaught `IndexOutOfBoundsException` from the click handler, which crashes the app. The list is never read by the calculation, so it only adds this failure mode.
- **State lifetime.** Entries live only in memory. A configuration change such as rotation recreates `MainActivity` and clears them.

These are tracked as issues on the [roadmap](../README.md#roadmap).

## 8. Natural extension: spreading over k semesters (not implemented)

The potential view generalizes directly. To reach $T$ after $m$ more semesters totalling $N$ credits, the required **average** GPA over those semesters is

$$
\bar{g}^{*} = T + \frac{C\,(T - \mathrm{CGPA})}{N}
$$

For example B with two more 24-credit semesters ($N = 48$), this gives $9 + 48 \times 0.592 / 48 \approx 9.59$. That is achievable, where the one-semester answer was not. Showing this when every one-semester row reads "Above 10" is a roadmap item.
