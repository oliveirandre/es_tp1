import React, { Component } from 'react';

class App extends Component {

  constructor(props) {
    super(props);
    this.state = {
      items: [],
      isLoaded: false,
    }
  }

  componentDidMount() {
    fetch('http://quotes.stormconsultancy.co.uk/random.json')
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
          <h2>{ items.quote }</h2>
          <p>By <span>{ items.author }</span> </p>
        </div>
      );
    }
  }
}

export default App;
