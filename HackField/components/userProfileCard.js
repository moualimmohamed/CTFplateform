import React from "react";
import Image from "next/image"
import SocialMedia from "./socialMedia";



const UserProfileCard = (props) => {
    return (
    
        <section className="w-full h-4/5 px-6 py-40">
            
            <div className="w-4/5 flex flex-col items-center justify-center px-6 py-8 mx-auto md:h-screen lg:py-0">
                <a href="#" className="flex items-center mb-6 text-2xl font-semibold text-gray-900 dark:text-white">
                <span>
                            <Image
                                src="/img/logo.svg"
                                alt="N"
                                width="32"
                                height="32"
                                className="w-20"
                            />
                </span>
                <h1 className="text-4xl">User<span className="text-white dark:text-teal-400 ">Profile</span></h1>
                </a>

                


                <div className="flex flex-col items-center bg-white dark:bg-gray-800 rounded-lg shadow w-full mx-px px-6 py-8">
                    {/* Up*/}
                    <div className="flex  flex-col w-full items-center text-center mb-8">
                        <div className="relative flex h-60 w-full justify-center rounded-xl bg-cover" >
                                {/* Background Image */}
                                <Image
                                        src={props.background}
                                        alt="N"
                                        className="absolute flex h-60 w-full justify-center rounded-xl bg-cover"
                                /> 
                                {/* Profile Image */}
                                <Image
                                    src={props.source}
                                    alt="N"
                                    className="rounded-full absolute -bottom-12 flex h-[120px] w-[120px] items-center justify-center border-[4px] border-white bg-green-300 dark:!border-teal-600"
                                />
                        </div> 
                        
                        <div class="mt-16 flex flex-col items-center">
                            <h1 className="text-xl font-bold text-green-300 ">{props.name}</h1>
                            <h4 className="text-white">Guru</h4>
                        </div>

                        <div className="mt-6 mb-3 flex gap-14 md:!gap-14">
                            <div className="dark:text-white text-gray-800"><span className="text-teal-400"><b>Score  </b></span>  {props.score}</div>
                            <div className="dark:text-white text-gray-800"><span className="text-teal-400"><b>Level  </b></span>  {props.level}</div>
                            <div className="dark:text-white text-gray-800"><span className="text-teal-400"><b>Team   </b></span>  {props.team}</div>
                            <div className="dark:text-white text-gray-800"><span className="text-teal-400"><b>Joined </b></span>  {props.creationDate}</div>
                        </div> 

                        <div>
                            <SocialMedia size="24" />
                        </div>
                    </div>   

                    {/* Bottom */}
                    
                    <div className="flex items-center w-full">
                        {/* Bottom left */}
                        <div name="bottom-left" className="px-4 py-4 mx- w-1/2">
                        Some Info
                            <form className="space-y-4 md:space-y-6 px-6 py-10 border border-teal-400 rounded-lg" action="#">
                                <div>
                                    <label htmlFor="Username" className="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Your Username</label>
                                    <input
                                    type="Username"
                                    name="Username"
                                    id="Username"
                                    className="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                                    placeholder="Abdellaouiii.m3d"
                                    required
                                    />
                                </div>
                                <div>
                                    <label htmlFor="email" className="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Your email</label>
                                    <input
                                    type="email"
                                    name="email"
                                    id="email"
                                    className="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                                    placeholder="name@ensa.com"
                                    required
                                    />
                                </div>
                                <div>
                                    <label htmlFor="password" className="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Password</label>
                                    <input
                                    type="password"
                                    name="password"
                                    id="password"
                                    placeholder="••••••••"
                                    className="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                                    required
                                    />
                                </div>
                                <div>
                                    <label htmlFor="password" className="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Confirm Password</label>
                                    <input
                                    type="password"
                                    name="password"
                                    id="password"
                                    placeholder="••••••••"
                                    className="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                                    required
                                    />
                                </div>
                                <button
                                    type="submit"
                                    className="w-full text-white bg-primary-600 hover:bg-primary-700 focus:ring-4 focus:outline-none focus:ring-primary-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-primary-600 dark:hover:bg-primary-700 dark:focus:ring-primary-800"
                                >
                                    <span className='w-full px-6 py-2 mt-3 text-center text-white bg-teal-600 rounded-md lg:ml-5'>Sign in</span>
                                </button>
                                <p className="text-sm font-light text-gray-500 dark:text-gray-400">
                                    Already a member? <a href="#" className="font-medium text-primary-600 hover:underline dark:text-primary-500"><span className='text-teal-400'>Log in </span></a>
                                </p>
                            </form>
                        </div>

                        {/* Bottom Right */}
                        <div name="bottom-right" className="px-4 py-4 mx-4 w-1/2">
                        Some Other Info
                            <form className="space-y-4 md:space-y-6 px-6 py-10 border border-teal-400 rounded-lg" action="#">
                                <div>
                                    <label htmlFor="Username" className="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Your Username</label>
                                    <input
                                    type="Username"
                                    name="Username"
                                    id="Username"
                                    className="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                                    placeholder="Abdellaouiii.m3d"
                                    required
                                    />
                                </div>
                                <div>
                                    <label htmlFor="email" className="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Your email</label>
                                    <input
                                    type="email"
                                    name="email"
                                    id="email"
                                    className="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                                    placeholder="name@ensa.com"
                                    required
                                    />
                                </div>
                                <div>
                                    <label htmlFor="password" className="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Password</label>
                                    <input
                                    type="password"
                                    name="password"
                                    id="password"
                                    placeholder="••••••••"
                                    className="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                                    required
                                    />
                                </div>
                                <div>
                                    <label htmlFor="password" className="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Confirm Password</label>
                                    <input
                                    type="password"
                                    name="password"
                                    id="password"
                                    placeholder="••••••••"
                                    className="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                                    required
                                    />
                                </div>
                                <button
                                    type="submit"
                                    className="w-full text-white bg-primary-600 hover:bg-primary-700 focus:ring-4 focus:outline-none focus:ring-primary-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-primary-600 dark:hover:bg-primary-700 dark:focus:ring-primary-800"
                                >
                                    <span className='w-full px-6 py-2 mt-3 text-center text-white bg-teal-600 rounded-md lg:ml-5'>Sign in</span>
                                </button>
                                <p className="text-sm font-light text-gray-500 dark:text-gray-400">
                                    Already a member? <a href="#" className="font-medium text-primary-600 hover:underline dark:text-primary-500"><span className='text-teal-400'>Log in </span></a>
                                </p>
                            </form>
                        </div>

                    </div>
                
                </div>
            </div>

        </section>



        
      
    );
  };
  
  export default UserProfileCard;