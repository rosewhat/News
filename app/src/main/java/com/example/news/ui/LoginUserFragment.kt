package com.example.news.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.news.R
import com.example.news.databinding.FragmentLoginUserBinding
import com.example.news.ui.viewModel.RoleModelViewModel
import com.google.android.material.snackbar.Snackbar


class LoginUserFragment : Fragment() {
    private var _binding: FragmentLoginUserBinding? = null
    private val binding: FragmentLoginUserBinding
        get() = _binding ?: throw RuntimeException(FRAGMENT_ERROR)

    private lateinit var viewModel: RoleModelViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[RoleModelViewModel::class.java]
        showRegisterFragment()
        binding.btLoginInAccount.setOnClickListener {
            val email = binding.etLoginEmail.text.toString()
            val password = binding.etLoginPassword.text.toString()
            if (email.isNotEmpty() && password.isNotEmpty()) {
                viewModel.authorizationUser(email, password)
                viewModel.getAuthorizationStatusLiveData().observe(viewLifecycleOwner) {
                    if (it == true) {
                        launchListTopNewsFragment()
                    } else {
                        showSnackBar(
                            LOGIN_ERROR_USER_DATA
                        )
                    }
                }
            } else {
                showSnackBar(LOGIN_EMPTY_DATA)
            }
        }
    }

    private fun showRegisterFragment() {
        binding.tvLoginLaunchToRegistrationFragment.setOnClickListener {
            launchRegisterFragment()
        }
    }

    private fun showSnackBar(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
    }

    private fun launchListTopNewsFragment() {
        requireActivity().supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                R.anim.slide_in_right,
                R.anim.slide_out_left,
                R.anim.slide_in_left,
                R.anim.slide_out_right
            )
            .replace(R.id.main_container, ListTopNewsFragment.newInstance())
            .commit()
    }

    private fun launchRegisterFragment() {
        requireActivity().supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                R.anim.slide_in_right,
                R.anim.slide_out_left,
                R.anim.slide_in_left,
                R.anim.slide_out_right
            )
            .replace(R.id.main_container, RegistrationUserFragment.newInstance())
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val FRAGMENT_ERROR = "LoginUserFragment is null"
        private const val LOGIN_EMPTY_DATA = "Not enough data"
        private const val LOGIN_ERROR_USER_DATA = "Wrong data"

        fun newInstance(): Fragment {
            return LoginUserFragment()
        }
    }
}