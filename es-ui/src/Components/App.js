import React, { Component } from 'react';

import Weather from "./Weather";

class App extends Component {

  constructor(props) {
    super(props);
    this.state = {
      items: [],
      isLoaded: false,
    }
  }

  componentDidMount() {
    //fetch('http://quotes.stormconsultancy.co.uk/random.json')
    fetch('http://localhost:8080/weather/publish/Aveiro')
      .then(res => res.json())
      .then(json => {
        this.setState({
          isLoaded: true,
          items: json,
        })
      });
  }

  render() {
    var { isLoaded, items } = this.state;
    if(!isLoaded) {
      return <div>Loading...</div>
    }
    else {
      return (
        <div id="root" className="App">
          <h2>{ items.content.globalIDLocal.City }</h2>
          <p>By <span>{ items.author }</span> </p>
          <div class="container text-center vcenter">
                    <h1> Local Weather</h1>
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-md-6 text-left">
                                    <h3 id="city"><i class="fa fa-map-marker"></i>&nbsp;&nbsp;<span>{items.content.globalIDLocal.City }</span></h3>
                                </div>
                                <div class="col-md-6 text-right temp-switch">
                                    <h3><input id="switch-temp" type="checkbox" data-off-color="info"  data-on-color="default" data-label-text="<i class='wi wi-thermometer'></i>" data-label-icon="<i class='wi wi-celsius'></i>" data-on-text="<i class='wi wi-fahrenheit'></i>" data-off-text="<i class='wi wi-celsius'></i>" /></h3>
                                </div>          
                            </div>
                            <hr/>
                            <div id="display">
                                <div class="row">
                                    <div class="col-md-6" id="weather-icon-display">
                                        <div id="weather-icon"><i class="wi wi-sleet"></i></div>
                                    </div>
                                    <div class="col-md-6" id="temperature">
                                        <h4>Today</h4>

                                        <p><span id="temp">73</span><sup>&deg;<span id="metric">F</span></sup></p>                 
                                        <h5>High: <span id="temp-max">{items.content.tMax}</span> / Low: <span id="temp-min">{ items.content.tMin }</span></h5>
                                    </div>
                                </div>
                            </div>
                            <hr id="hr-bottom" />
                            <div class="row" id="weather-condition">
                                <div class="col-md-2 col-md-offset-1">
                                    <h6><i class="wi wi-cloud"></i></h6>
                                    <p id="condition">{ items.content.idWeatherType.descWeatherType } </p>
                                </div>
                                <div class="col-md-2">
                                    <h6><i class="wi wi-humidity"></i></h6>
                                    <p id="humidity">10%</p>
                                </div>
                                <div class="col-md-2">
                                    <h6><i class="wi wi-strong-wind"></i></h6>
                                    <p id="wind">windy 20 NE</p>
                                </div>
                                <div class="col-md-2">
                                    <h6><i class="wi wi-barometer"></i></h6>
                                    <p id="hpa">10 psi</p>
                                </div>
                                <div class="col-md-2">
                                    <h6><i class="wi wi-showers"></i></h6>
                                    <p id="prec">10%</p>
                                </div>          
                            </div>
                            <hr/>
                        </div>
                    </div>
               </div>
        </div>
      );
    }
  }
}

export default App;
