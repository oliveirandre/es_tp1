import React, { Component } from 'react';

const API = 'http://localhost:8080';

class Weather extends Component {
        constructor(props) {
            super(props);
            this.state = {
                weather: [],
                isLoaded: false,
                error : null,
            };
            
	}
        
        componentDidMount() {
            fetch(API + '/publish/${local}')
                .then(res => res.json())
                .then(json => {
                    this.setState({
                        isLoaded: true,
                        items: json,
                    })
                })
                .catch(error => this.setState({
                   error,
                   isLoading: false
                }));
        }
}

export default Weather;