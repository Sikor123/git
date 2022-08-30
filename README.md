## How to run
1) Build project using gradle -
`gradle build`
2) build docker image -
`docker build -t myorg/gitapp . `
3) run docker image -
` docker run -p 8080:8080 myorg/gitapp -d`

