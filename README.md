# MultiTranslate

A Kotlin library for translation!

- `🚀` Translate through multiple different providers
- `💬` Translate through one function
- `❓` Detect the language of any text
- `🥔` some more buzzwords because i'm desperate for github stars
- `🌱` Carbon neutral! Environmentally friendly!

### Basic Usage

Here's how to translate text using DeepL translate.

```kt
val translator = Translator.Builder().provider(Provider.DEEPL).key("DeepL-Auth-Key abcdabcdabcdabcd").build()

translator.translate("Hello, world!", Language.SPANISH, Language.ENGLISH)
// > ¡Hola, mundo!
```

### FAQ

- A language I want is missing!
    - Make a PR.
- Why don't you test your code?
    - Because testing is for nerds.
    - I'm totally not just too lazy.
- oh my gosh this code is horrendous
    - deal with it


*When the first stable release is available.
