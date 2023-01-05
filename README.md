```sh
sbt logstage-json/run
```
out:
```
[info] running example.Example
I 2023-01-05T21:09:16.484 (ConfigLoader.scala:123)  …ader.LocalFSImpl.loadConfig [133:zio-default-async-1] Using system properties with fallback config files=
- resource:application.conf (available: /application.conf)
- resource:application-reference.conf (missing)
- resource:application-reference-dev.conf (missing)
- resource:common.conf (missing)
- resource:common-reference.conf (missing)
- resource:common-reference-dev.conf (missing)
```