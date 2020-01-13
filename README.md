# outlier-detection

1.To install application , run batch script install_app.bat . This script will build the maven artifcats and docker images
2.To run the application , run batch script start_all.bat . This will start all the components in docker environment. Each component will run in a separate docker container
(see docker-compose.yml)
3.To run the E2E test , run batch script run_test.bat.

Application Endpoints : 
http://localhost:8081/swagger-ui.html - producer swagger-ui
http://localhost:8083/swagger-ui.html - web server swagger-ui
http://localhost:5601/app/kibana - kibana dashboard
