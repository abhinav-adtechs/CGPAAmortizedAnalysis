# Changelog

All notable changes to this project are documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/), and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html). Versions were tagged retroactively at milestone commits from the original 2016 history and match `versionName "1.0"` in `app/build.gradle`.

## [Unreleased]

### Added

- README with quick start (including the missing root Gradle files), configuration reference and roadmap.
- `docs/ALGORITHM.md`: derivation of the CGPA and minimum-next-semester-GPA formulas, the amortized-analysis view, worked examples and edge cases.
- `docs/ARCHITECTURE.md`: MVC package map, EventBus flow, build configuration.
- MIT `LICENSE` and `CONTRIBUTING.md`.

## [1.0.1] - 2016-06-21

### Changed

- The minimum-GPA table shows **"Above 10"** instead of an unreachable number when the target can't be reached in one semester (`7f1c391`).
- Sources reorganized from one flat package into `Model/Entities`, `Model/EventBusEvents`, `View/Activities`, `View/Fragments`, `View/CustomViews` and `Controller`. The manifest was updated to match. There are no behaviour changes beyond the label above (`822bb5a`).

## [1.0.0] - 2016-05-10

First complete version: enter semesters, see your CGPA, and get the minimum GPA you need next semester.

### Added

- Eight-page semester pager with GPA and credit inputs, an ink page indicator and a custom button style (`a955fdd`).
- MPAndroidChart bar chart of semester GPAs, updated as semesters are added (`2bb2876`, `4beb519`).
- `CalculateCGPA` (credit-weighted CGPA) and the `DataUpdateEvent` EventBus flow from pager to activity (`4beb519`, `1a7460b`, `1b55314`, `b0ef57b`, `bd41e9e`).
- `CalculateAmortizedGPA.getMinPredictedGPA(n)`, the minimum next-semester GPA to keep a 9.0 CGPA, and the `AmortizedAdapter` table for 16–27 credits (`1b55314`, `3a838a5`).
- `AlertDialogFragment`, which shows the table after every submission and receives the semester list through a sticky `DataListEvent` (`99385ee`, `89ad7e8`).
- `AlertFragment` demo activity with hard-coded sample semesters (`3a838a5`).
- Input validation (non-empty fields, 0 ≤ GPA ≤ 10) and the CGPA verdict line (`b0ef57b`, `bd41e9e`).

[Unreleased]: https://github.com/abhinav-adtechs/CGPAAmortizedAnalysis/compare/v1.0.1...HEAD
[1.0.1]: https://github.com/abhinav-adtechs/CGPAAmortizedAnalysis/compare/v1.0.0...v1.0.1
[1.0.0]: https://github.com/abhinav-adtechs/CGPAAmortizedAnalysis/commits/v1.0.0
