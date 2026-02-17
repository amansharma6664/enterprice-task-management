import React from 'react';
import '../styles/TaskCard.css';

const TaskCard = ({ task, onEdit, onDelete }) => {
  const getPriorityClass = (priority) => {
    return `priority-${priority.toLowerCase()}`;
  };

  const getStatusClass = (status) => {
    return `status-${status.toLowerCase().replace('_', '-')}`;
  };

  const formatDate = (date) => {
    if (!date) return 'No due date';
    return new Date(date).toLocaleDateString();
  };

  return (
    <div className="task-card">
      <div className="task-header">
        <h3>{task.title}</h3>
        <div className="task-badges">
          <span className={`badge ${getPriorityClass(task.priority)}`}>
            {task.priority}
          </span>
          <span className={`badge ${getStatusClass(task.status)}`}>
            {task.status.replace('_', ' ')}
          </span>
        </div>
      </div>
      
      <p className="task-description">{task.description}</p>
      
      <div className="task-details">
        <div className="task-info">
          <span className="label">Assigned to:</span>
          <span>{task.assignedToName || 'Unassigned'}</span>
        </div>
        <div className="task-info">
          <span className="label">Due date:</span>
          <span>{formatDate(task.dueDate)}</span>
        </div>
        {task.teamName && (
          <div className="task-info">
            <span className="label">Team:</span>
            <span>{task.teamName}</span>
          </div>
        )}
      </div>
      
      <div className="task-actions">
        <button className="btn-edit" onClick={() => onEdit(task)}>
          Edit
        </button>
        <button className="btn-delete" onClick={() => onDelete(task.id)}>
          Delete
        </button>
      </div>
    </div>
  );
};

export default TaskCard;
