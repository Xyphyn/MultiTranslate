# MultiTranslate

A Kotlin library for translating using multiple providers.

MultiTranslate currently* supports:
    
- LibreTranslate
- DeepL Translate

### Usage

Here is an example on how to use this garbage.

```kt
val translator = Translator.Builder().provider(Provider.DEEPL).key("DeepL-Auth-Key abcdabcdabcdabcd").build()

translator.translate("Hello, world!", Language.SPANISH, Language.ENGLISH)
// > ¡Hola, mundo!
```

### FAQ

- Why don't you test your code?
    - Because testing is for nerds.
    - I'm totally not just too lazy.
- oh my gosh this code is horrendous
    - deal with it


*When the first stable release is available.
