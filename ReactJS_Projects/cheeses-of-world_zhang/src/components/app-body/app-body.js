import React from 'react';
import './app-body.css';
import {CheeseList} from '../cheese-list/cheese-list';
import {NewCheese} from '../new-cheese/new-cheese';

export class AppBody extends React.Component {
	
	id = 5;

	state = { // states saves cheesse list
		cheeseList: [
			{
				id: 1,
				cheeseName: "Mozarella",
				cheeseNation: "Italy"
			},
			{
				id: 2,
				cheeseName: "Stilton",
				cheeseNation: "Great Britain"
			},
			{
				id: 3,
				cheeseName: "Gouda",
				cheeseNation: "Netherlands"
			},
			{
				id: 4,
				cheeseName: "Danish Blue",
				cheeseNation: "Denmark"
			}
		]
	}

	handleSave = (cheese) => { // save cheese
		const {cheeseList} = this.state;
		this.setState({
			cheeseList: cheeseList.concat({id: this.id++, ...cheese})
		});
	}

	handleDelete = (cheeseId) => { // delete cheese
		const {cheeseList} = this.state;
		this.setState({
			cheeseList: cheeseList.filter(cheese => cheese.id !== cheeseId)
		});
	}

	handleUpdate = (cheeseId, data) => { // update cheese
		const {cheeseList} = this.state;
		this.setState({
			cheeseList: cheeseList.map(cheese => cheese.id !== cheeseId ? cheese : {...cheese, ...data})
		});

	}

	render() {
	    return (
	    	<div className="app-body">
	    		<CheeseList // cheese list
	    			cheeseList={this.state.cheeseList}
	    			onDelete={this.handleDelete}
	    			onUpdate={this.handleUpdate}/>
	    		<NewCheese onSave={this.handleSave}/> {/* cheese creation ui */}
	    	</div>
	      
	    );
	}
}
