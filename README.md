<p align="center"><img src ="http://static.norcane.cz/noble/noble-logo.png" width="400" /></p>

*Norcane blog engine (noble)* is the blog engine written **by developers for developers**. It's
built using the [Play Framework](https://www.playframework.com), so it can be seamlessly embedded
into your existing *Play* applications. But the main idea is to **keep it simple**. No obscure ways
of storing blog posts, no annoying embedded *WYSIWYG* editors for more bizarre blog post format.
*Noble* by default uses as much tools you probably use every day: [Git](https://git-scm.com) as a
blog posts and assets storage, [Markdown](http://daringfireball.net/projects/markdown/) for blog
posts format and all *Play*'s goodies for writing themes, such as
[Twirl](https://www.playframework.com/documentation/2.5.x/ScalaTemplates) templates,
[Sass](http://sass-lang.com) or [Less](http://lesscss.org) for styling,
[Scala.js](https://www.scala-js.org) for scripting and much more! Don't like this selection? Never
mind, because **noble is modular**! Wanna *blog storage* that uses database? Or *blog post format*
that uses the *Wiki* syntax? No problem, just write your own.

> Please note that despite our best efforts, *noble* was primarily written for our internal purposes
and is still under heavy development. It means it's NOT feature complete yet (see the
*Planned features* chapter below) and its API may change until it reaches the final version.

## Key features
- Seamless integration into any existing *Play* application.
- Modular blog storage support. Bundled implementation uses *Git* with directory structure similar
  to [Jekyll](https://jekyllrb.com).
- Modular blog post format support, your blog posts can use in **multiple blog post formats**
  within a single blog. Default bundled implementation uses *Markdown* with *YAML*
- Modular themes support. Bundled with *Humane* theme, lightweight responsive theme.
- Support for *static pages*.
- Supports navigable blog history and tag cloud.
- Generated *Atom* feed without any further configuration.
- Single installation can serve one or more blogs, written by one or more authors.

## Planned features
- [ ] *XML*/*JSON* API for better integration with client-side code.

## Try it out
*Noble* source code distribution contains the example blog called *Minimal*, which you can easily
try out using the following steps:

1. **Clone the *noble* repository**  
   `git clone https://github.com/norcane/noble.git`
2. **Go to the directory and run the *Minimal* example blog**  
   Note that this step requires the [Scala Build Tool (SBT)](http://www.scala-sbt.org) installed.

   ```
   $ cd noble
   $ sbt minimal/run
   ```

   At this moment, *Example* blog should be accessible by your browser at
   [http://localhost:9000](http://localhost:9000).

## Add it to your SBT project
Stable *Noble* releases are available via the [Bintray Repo](https://bintray.com/norcane), to use it
as a SBT dependency, paste this code to your *build.sbt*:

```
resolvers += Resolver.bintrayRepo("norcane", "noble")
libraryDependencies += "com.norcane.noble" %% "noble" % "<release_version>"
```

## Documentation
The [noble wiki](https://github.com/norcane/noble/wiki) serves as the main project documentation and
covers all steps needed to successfully embed *noble* into your *Play* application and write your
custom themes.

## Maintainers
Below is the list of current project maintainers. Feel free to contact us in case of any troubles.

* V??clav ??vejcar - [#vaclavsvejcar](https://github.com/vaclavsvejcar)
* J??n Na?? - [#jannad](https://github.com/jannad)

## License
This project is licensed under the terms of the
[Apache License, version 2.0](https://www.apache.org/licenses/LICENSE-2.0).
