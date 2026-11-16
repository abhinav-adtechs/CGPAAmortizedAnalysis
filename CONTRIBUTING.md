# Contributing to CGPAAmortizedAnalysis

Thanks for stopping by! This is a 2016 student project. The most useful contributions are on the [roadmap](README.md#roadmap): input bugs, tests, a configurable target CGPA, multi-semester planning, and a build that opens in today's Android Studio.

Please follow the [Code of Conduct](https://github.com/abhinav-adtechs/.github/blob/main/CODE_OF_CONDUCT.md). For security concerns, see [SECURITY.md](https://github.com/abhinav-adtechs/.github/blob/main/SECURITY.md) rather than opening a public issue.

## Development setup

1. Fork and clone the repo.
2. The repo ships only the `app/` module. Add a root `settings.gradle` and `build.gradle` as shown in the README's [Quick start](README.md#quick-start), or follow the tip there to move the sources into a fresh modern project. **Don't commit these root files** unless your PR is the "add root Gradle files / migrate the build" roadmap item.
3. Run the `app` configuration on a device or emulator on API 22+.
4. Sanity check: enter the sample semesters from the README (9.4/24, 9.3/26, 9.2/24, 9.0/24). The final dialog should read about 7.61 at 16 credits and about 8.18 at 27.

## Working on the maths

- [`docs/ALGORITHM.md`](docs/ALGORITHM.md) is the spec. If you change `CalculateCGPA` or `CalculateAmortizedGPA`, update the formulas, line references and worked examples in the same PR.
- Keep the calculators free of Android UI code so they stay unit-testable. `CalculateCGPA` currently calls `android.util.Log.d`. In JVM unit tests, either remove that call or set `testOptions.unitTests.returnDefaultValues = true`.
- Prefer explicit inputs (for example, a target CGPA parameter) over new hard-coded constants.

## Branches and commits

- Branch from `master` with a descriptive name, such as `fix/duplicate-semester` or `feat/configurable-target`.
- Use [Conventional Commits](https://www.conventionalcommits.org/):

  ```text
  fix(gpa): replace a semester's entry instead of appending a duplicate
  feat(estimate): make the target CGPA configurable
  test(model): cover CalculateAmortizedGPA reachability bound
  docs(algorithm): add multi-semester example
  build: add root settings.gradle and Gradle wrapper
  ```

- Keep commits focused, and write the subject in the imperative mood in under 72 characters.

## Filing issues

- **Bugs:** list the exact inputs (GPA, credits, which semester pages, in what order), what you expected, what you saw, and the device/emulator API level. Include a stack trace for crashes.
- **Features:** describe the student's question you want the app to answer, and how it fits the formulas in `docs/ALGORITHM.md`.
- Search [existing issues](https://github.com/abhinav-adtechs/CGPAAmortizedAnalysis/issues?q=is%3Aissue) first.

## Pull request checklist

- [ ] The app builds and runs (state your Android Studio version and device/emulator API level).
- [ ] The change is scoped to one concern, with a Conventional Commit title.
- [ ] Maths changes come with unit tests and an updated `docs/ALGORITHM.md`.
- [ ] UI changes include a before/after screenshot.
- [ ] No IDE or machine-specific files (`.idea/`, `local.properties`, `build/`) added.

## License

By contributing, you agree that your contributions are licensed under the [MIT License](LICENSE).
