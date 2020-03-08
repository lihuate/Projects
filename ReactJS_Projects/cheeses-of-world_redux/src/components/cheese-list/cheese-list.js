import React from 'react';
import CheeseItem from '../cheese-item/cheese-item';
import './cheese-list.css';
import {connect} from 'react-redux'

// represents cheese list
 class CheeseList extends React.Component {

	static defaultProps = {
		list: [],
		onDelete: () => console.warn('onDelete not defined')
	} 

	render() {
		const { cheeseList, onDelete, onUpdate } = this.props;
		// create jsx list from data
		const list = cheeseList.map((cheese) => {
		 return (
		 	<CheeseItem 
		 		key={cheese.id} 
		 		info={cheese}
		 		onDelete={onDelete}
		 		onUpdate={onUpdate}/>
			);
		});

		// render
	    return (
	    	<div className="cheese-list">
	    		<h1 className="title">Cheese List</h1>
	    		<ul className="list">
	    			{list}
	    		</ul>
	    	</div>
	    );
	}
}


const mapStateToProps = state => ({
	 cheeseList : state.todos
  })
  

export default connect(
    mapStateToProps,
)(CheeseList)