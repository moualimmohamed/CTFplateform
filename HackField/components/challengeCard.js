import React from 'react';


const ChallengeCard = (props) => {
  return (
        <div className="bg-gray-800 text-white p-6 rounded-lg shadow-md">
          <h2 className="text-2xl font-bold mb-4">{props.name}</h2>
          <div className="flex justify-between text-sm">
            <p className="text-gray-400">{props.category}</p>
            <p className="text-teal-400">{props.points} Points</p>
          </div>
        </div>

  );

}


export default ChallengeCard;