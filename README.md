### Hexlet tests and linter status:
[![.github/workflows/main.yml](https://github.com/C0deFixer/java-project-71/actions/workflows/main.yml/badge.svg?branch=main&event=push)](https://github.com/C0deFixer/java-project-71/actions/workflows/main.yml)[![Actions Status](https://github.com/C0deFixer/java-project-71/actions/workflows/hexlet-check.yml/badge.svg)](https://github.com/C0deFixer/java-project-71/actions)
[![Maintainability](https://api.codeclimate.com/v1/badges/2f7f2e634e42feffa981/maintainability)](https://codeclimate.com/github/C0deFixer/java-project-71/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/2f7f2e634e42feffa981/test_coverage)](https://codeclimate.com/github/C0deFixer/java-project-71/test_coverage)

Utility provide Comparing two configuration files and shows a difference.
json and yaml files formats supported, 
result could be output in stylish (default), plain,  or json format

**Usage: gendiff [-hV] [-f=format] filepath1 filepath2**
```
filepath1         path to first file
filepath2         path to second file
-f, --format=format   output format [default: stylish]
-h, --help            Show this help message and exit.
-V, --version         Print version information and exit.
```
[![asciicast](https://asciinema.org/a/uQmCwXTJj9ZCXmMUpAm3Waipd.svg)](https://asciinema.org/a/fdAe8VrU6rt70lq7pO5Dwz6Zr)
