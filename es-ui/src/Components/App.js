import React, { Component } from 'react';

import Weather from "./Weather";

export default class App extends Component {
    
    constructor(props) {
      super(props);
      this.state = {
        items: [],
        isLoaded: false,
      }
    }

  componentDidMount() {
    const { params } = this.props.match  
    fetch('http://localhost:8080/weather/publish/'+params.local)
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
                <h2 style={{'font-size': '60px'}}> Metereologia</h2>
                    <div class = "panel panel-default" style={{'height': '60%','weight': '60%' }}>
                        <div class = "panel-body">
                            <div class = "col-md-6 text-left" >
                                <h3 id = "city" style={{'color' : '#333' }} ><i class="fa fa-map-marker"/>&nbsp;&nbsp;<span>{ items.content.globalIDLocal.City}</span></h3>
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
                                    <p> <span id = "temp" style={{'color' : '#333', 'font-size' : '200px' }}> { items.content.tMax} </span><sup style={{'color' : '#333' }}>&deg;<span id="metric" style={{'color' : '#333' }}>C</span></sup></p>
                                    <h5> High: <span id = "temp-max"> { items.content.tMax} </span> / Low: <span id = "temp-min"> { items.content.tMin} </span></h5>
                                </div>
                            </div>
                        </div>
                        <hr id = "hr-bottom"/>
                        <div class = "row" id = "weather-condition" >
                            <div class = "col-md-2 col-md-offset-1">
                            <h6><i class = "wi wi-cloud"> </i></h6 >
                            <p id = "condition" style={{'color' : '#333' }}> { items.content.idWeatherType.descWeatherType}</p>
                        </div>
                        <div class = "col-md-2" >
                            <h6><i class = "wi wi-time-4"></i></h6 >
                            <p id = "humidity" style={{'color' : '#333' }}> { items.content.forecastDate} </p>
                        </div>
                        <div class = "col-md-2" >
                            <h6> <i class = "wi wi-strong-wind"></i></h6 >
                            <p id = "wind" style={{'color' : '#333' }}> { items.content.classWindSpeed.descClassWindSpeed} , { items.content.classWindDir}</p>
                        </div>
                        <div class = "col-md-2" >
                            <p id = "hpa" style={{'color' : '#333' }}> Latitude : {items.content.latitude}</p>
                            <p id = "hpa" style={{'color' : '#333' }}> Longitude : {items.content.longitude}</p>
                        </div>
                        <div class = "col-md-2" >
                            <h6><i class = "wi wi-showers"> </i></h6>
                            <p id = "prec" style={{'color' : '#333' }}> {items.content.probPrecipita} % </p>
                        </div>
                    </div>
                </div>
                <h2 style={{'font-size': '30px'}} >Failures are only failures when we don’t learn from them, because when we learn from them they become lessons.</h2>
                <p style={{'font-size': '30px'}} >By <span>Jay Shetty</span> </p>
            </div>
        );
    }
  }
}
