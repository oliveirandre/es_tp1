import React, { Component } from 'react';

class Weather extends Component {
        constructor(props) {
            super(props);
            this.state = {
                name: String,
                tMax : String,
                tMin : String,
                isLoaded: false,
            };
            
	}
        
        render() {
                var { isLoaded, name, tMax, tMin } = this.state;
                return (
                    <div class = "panel panel-default">
                        <div class = "panel-body">
                            <div class = "col-md-6 text-left" >
                                <h3 id = "city"><i class="fa fa-map-marker"/>&nbsp;&nbsp;<span></span></h3>
                            </div>        
                        </div>
                        <hr/>
                        <div id = "display">
                            <div class = "row">
                                <div class = "col-md-6" id = "weather-icon-display">
                                    <div id = "weather-icon"><i class = "wi wi-sleet"></i></div>
                                </div>
                                <div class = "col-md-6" id = "temperature">
                                    <h4> Today </h4>
                                    <p> <span id = "temp"> 73 </span><sup>&deg;<span id="metric">C</span></sup></p>
                                    <h5> High: <span id = "temp-max"> tMax </span> / Low: <span id = "temp-min"> tMin </span></h5>
                                </div>
                            </div>
                        </div>
                        <hr id = "hr-bottom"/>
                        <div class = "row" id = "weather-condition">
                            <div class = "col-md-2 col-md-offset-1">
                            <h6><i class = "wi wi-cloud"> </i></h6 >
                            <p id = "condition"> tMax</p>
                        </div>
                        <div class = "col-md-2" >
                            <h6><i class = "wi wi-humidity"></i></h6 >
                            <p id = "humidity"> 10 % </p>
                        </div>
                        <div class = "col-md-2" >
                            <h6> <i class = "wi wi-strong-wind"></i></h6 >
                            <p id = "wind" > </p>
                        </div>
                        <div class = "col-md-2" >
                            <h6 > < i class = "wi wi-barometer" > < /i></h6 >
                            <p id = "hpa"> 10 psi </p>
                        </div>
                        <div class = "col-md-2" >
                            <h6><i class = "wi wi-showers"> </i></h6>
                            <p id = "prec"> 10 % </p>
                        </div>
                    </div>
                </div>
                );
        }
}

export default Weather;