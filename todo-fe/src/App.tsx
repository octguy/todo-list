import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'

function App() {
  const [count, setCount] = useState(0)

  return (
    <div className="min-h-screen bg-gradient-to-br from-blue-50 to-indigo-100 flex items-center justify-center p-4">
      <div className="max-w-md mx-auto text-center bg-white rounded-xl shadow-lg p-8">
        <div className="flex justify-center space-x-4 mb-8">
          <a href="https://vite.dev" target="_blank" className="block">
            <img
              src={viteLogo}
              className="h-16 w-16 hover:drop-shadow-lg transition-all duration-300"
              alt="Vite logo"
            />
          </a>
          <a href="https://react.dev" target="_blank" className="block">
            <img
              src={reactLogo}
              className="h-16 w-16 animate-spin hover:drop-shadow-lg transition-all duration-300"
              alt="React logo"
            />
          </a>
        </div>

        <h1 className="text-4xl font-bold text-gray-800 mb-8">Vite + React</h1>

        <div className="bg-gray-50 rounded-lg p-6 mb-6">
          <button
            onClick={() => setCount((count) => count + 1)}
            className="bg-blue-500 hover:bg-blue-600 text-white font-semibold py-3 px-6 rounded-lg transition-colors duration-200 transform hover:scale-105"
          >
            count is {count}
          </button>
          <p className="mt-4 text-gray-600">
            Edit <code className="bg-gray-200 px-2 py-1 rounded text-sm">src/App.tsx</code> and save to test HMR
          </p>
        </div>

        <p className="text-gray-500 text-sm">
          Click on the Vite and React logos to learn more
        </p>
      </div>
    </div>
  )
}

export default App