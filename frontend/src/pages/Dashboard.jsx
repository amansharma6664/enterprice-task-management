import React, { useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { fetchTasks, createTask, updateTask, deleteTask } from '../redux/taskSlice';
import TaskList from '../components/TaskList';
import TaskForm from '../components/TaskForm';
import '../styles/Dashboard.css';

const Dashboard = () => {
  const dispatch = useDispatch();
  const { tasks, isLoading } = useSelector((state) => state.tasks);
  const { user } = useSelector((state) => state.auth);
  const [showForm, setShowForm] = useState(false);
  const [editingTask, setEditingTask] = useState(null);
  const [filter, setFilter] = useState('ALL');

  useEffect(() => {
    dispatch(fetchTasks());
  }, [dispatch]);

  const handleCreateTask = (taskData) => {
    dispatch(createTask(taskData));
    setShowForm(false);
  };

  const handleUpdateTask = (id, taskData) => {
    dispatch(updateTask({ id, taskData }));
    setEditingTask(null);
  };

  const handleDeleteTask = (id) => {
    if (window.confirm('Are you sure you want to delete this task?')) {
      dispatch(deleteTask(id));
    }
  };

  const handleEditClick = (task) => {
    setEditingTask(task);
    setShowForm(true);
  };

  const filteredTasks = tasks.filter(task => {
    if (filter === 'ALL') return true;
    if (filter === 'MY_TASKS') return task.assignedToId === user.id;
    return task.status === filter;
  });

  const taskStats = {
    total: tasks.length,
    todo: tasks.filter(t => t.status === 'TODO').length,
    inProgress: tasks.filter(t => t.status === 'IN_PROGRESS').length,
    completed: tasks.filter(t => t.status === 'COMPLETED').length,
  };

  return (
    <div className="dashboard">
      <div className="dashboard-header">
        <h1>Task Dashboard</h1>
        <button className="btn-primary" onClick={() => setShowForm(true)}>
          + Create Task
        </button>
      </div>

      <div className="stats-container">
        <div className="stat-card">
          <h3>Total Tasks</h3>
          <p className="stat-number">{taskStats.total}</p>
        </div>
        <div className="stat-card">
          <h3>To Do</h3>
          <p className="stat-number">{taskStats.todo}</p>
        </div>
        <div className="stat-card">
          <h3>In Progress</h3>
          <p className="stat-number">{taskStats.inProgress}</p>
        </div>
        <div className="stat-card">
          <h3>Completed</h3>
          <p className="stat-number">{taskStats.completed}</p>
        </div>
      </div>

      <div className="filter-container">
        <button 
          className={filter === 'ALL' ? 'filter-btn active' : 'filter-btn'}
          onClick={() => setFilter('ALL')}
        >
          All Tasks
        </button>
        <button 
          className={filter === 'MY_TASKS' ? 'filter-btn active' : 'filter-btn'}
          onClick={() => setFilter('MY_TASKS')}
        >
          My Tasks
        </button>
        <button 
          className={filter === 'TODO' ? 'filter-btn active' : 'filter-btn'}
          onClick={() => setFilter('TODO')}
        >
          To Do
        </button>
        <button 
          className={filter === 'IN_PROGRESS' ? 'filter-btn active' : 'filter-btn'}
          onClick={() => setFilter('IN_PROGRESS')}
        >
          In Progress
        </button>
        <button 
          className={filter === 'COMPLETED' ? 'filter-btn active' : 'filter-btn'}
          onClick={() => setFilter('COMPLETED')}
        >
          Completed
        </button>
      </div>

      {showForm && (
        <TaskForm
          task={editingTask}
          onSubmit={editingTask ? 
            (data) => handleUpdateTask(editingTask.id, data) : 
            handleCreateTask
          }
          onCancel={() => {
            setShowForm(false);
            setEditingTask(null);
          }}
        />
      )}

      {isLoading ? (
        <div className="loading">Loading tasks...</div>
      ) : (
        <TaskList
          tasks={filteredTasks}
          onEdit={handleEditClick}
          onDelete={handleDeleteTask}
        />
      )}
    </div>
  );
};

export default Dashboard;
